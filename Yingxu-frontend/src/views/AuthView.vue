<script setup lang="ts">
import { computed, ref, watch } from 'vue'
import {
  PhArrowLeft as ArrowLeft,
  PhArrowRight as ArrowRight,
  PhEye as Eye,
  PhEyeSlash as EyeSlash,
  PhSparkle as Sparkle,
} from '@phosphor-icons/vue'
import BrandLogo from '../components/BrandLogo.vue'
import hero from '../assets/coastal-auth.png'
import heroHandwriting from '../assets/hero-handwriting.png'
import filmRibbon from '../assets/film-ribbon.png'

type Page = 'login' | 'register'
type Channel = 'phone' | 'email'
type LoginMethod = 'code' | 'password'

const { page } = defineProps<{ page: Page }>()

const channel = ref<Channel>('phone')
const loginMethod = ref<LoginMethod>('code')
const phone = ref('')
const email = ref('')
const code = ref('')
const password = ref('')
const accepted = ref(false)
const remember = ref(true)
const showPassword = ref(false)
const message = ref('')
const messageType = ref<'error' | 'info'>('info')

const isRegister = computed(() => page === 'register')
const needsCode = computed(() => isRegister.value || loginMethod.value === 'code')
const needsPassword = computed(() => isRegister.value || loginMethod.value === 'password')

watch(() => page, () => {
  channel.value = 'phone'
  loginMethod.value = 'code'
  message.value = ''
})

function setChannel(next: Channel) {
  channel.value = next
  if (!isRegister.value && next === 'email') loginMethod.value = 'password'
  message.value = ''
}

function setLoginMethod(next: LoginMethod) {
  loginMethod.value = next
  message.value = ''
}

function showMessage(text: string, type: 'error' | 'info' = 'info') {
  message.value = text
  messageType.value = type
}

function validIdentifier() {
  if (channel.value === 'phone') {
    if (!/^1\d{10}$/.test(phone.value.trim())) {
      showMessage('请输入 11 位中国大陆手机号。', 'error')
      return false
    }
  } else if (!/^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(email.value.trim())) {
    showMessage('请输入有效的邮箱地址。', 'error')
    return false
  }
  return true
}

function sendCode() {
  if (!validIdentifier()) return
  showMessage('验证码服务尚未接入，当前无法发送验证码。')
}

function submit() {
  if (!validIdentifier()) return
  if (needsCode.value && !/^\d{6}$/.test(code.value.trim())) {
    showMessage('请输入 6 位验证码。', 'error')
    return
  }
  if (needsPassword.value && !/^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d!@#$%^&*._-]{8,16}$/.test(password.value)) {
    showMessage('密码需为 8–16 位，并包含字母和数字。', 'error')
    return
  }
  if (isRegister.value && !accepted.value) {
    showMessage('请先阅读并同意用户协议和隐私政策。', 'error')
    return
  }
  showMessage('表单校验已通过。账号服务暂未接入此页面，稍后可继续完成' + (isRegister.value ? '注册' : '登录') + '。')
}
</script>

<template>
  <main class="auth-page" :style="{ backgroundImage: `url(${hero})` }">
    <header class="auth-header page-width">
      <BrandLogo />
      <RouterLink to="/" class="auth-back"><ArrowLeft :size="18" /> 返回首页</RouterLink>
      <p>用 AI 让每一段灵感，都合成一支好视频</p>
    </header>

    <div class="auth-layout page-width">
      <section class="auth-story" :aria-label="isRegister ? '开启视频创作' : '继续视频创作'">
        <p class="eyebrow auth-story__eyebrow"><Sparkle :size="16" weight="fill" /> AI VIDEO CREATION</p>
        <h1 v-if="isRegister">从一句灵感，<br />开始你的<span>第一支视频</span></h1>
        <h1 v-else>欢迎回来，<br />继续创作你的<span>下一支视频</span></h1>
        <p v-if="isRegister" class="auth-story__artline">用 AI 将你的想法变成具有电影质感的视频<br />从脚本、画面到配乐，一站式完成</p>
        <p v-else>打开工作台，接着完成你的每一个镜头</p>
        <img :src="heroHandwriting" alt="让每一个灵感，都有一部自己的电影" class="auth-story__handwriting" />
        <img :src="filmRibbon" alt="" class="auth-story__ribbon" />
      </section>

      <section class="auth-card" :aria-labelledby="isRegister ? 'register-title' : 'login-title'">
        <div class="auth-card__heading">
          <p class="eyebrow">YINGXU AI</p>
          <h2 v-if="isRegister" id="register-title">欢迎来到<span>映序AI</span></h2>
          <h2 v-else id="login-title">登录<span>映序AI</span></h2>
          <p>{{ isRegister ? '注册后即可体验 AI 视频生成与对话剪辑' : '继续你的 AI 视频创作' }}</p>
        </div>

        <form class="auth-form" novalidate @submit.prevent="submit">
          <div v-if="isRegister" class="auth-tabs" role="tablist" aria-label="注册方式">
            <button type="button" role="tab" :aria-selected="channel === 'phone'" :class="{ 'is-active': channel === 'phone' }" @click="setChannel('phone')">手机号注册</button>
            <button type="button" role="tab" :aria-selected="channel === 'email'" :class="{ 'is-active': channel === 'email' }" @click="setChannel('email')">邮箱注册</button>
          </div>
          <div v-else class="auth-tabs" role="tablist" aria-label="登录方式">
            <button type="button" role="tab" :aria-selected="loginMethod === 'code'" :class="{ 'is-active': loginMethod === 'code' }" @click="setLoginMethod('code')">验证码登录</button>
            <button type="button" role="tab" :aria-selected="loginMethod === 'password'" :class="{ 'is-active': loginMethod === 'password' }" @click="setLoginMethod('password')">密码登录</button>
          </div>

          <div class="form-field">
            <label :for="channel === 'phone' ? 'phone' : 'email'">{{ channel === 'phone' ? '手机号' : '邮箱地址' }}</label>
            <div v-if="channel === 'phone'" class="input-wrap phone-input"><span class="country-code">+86</span><input id="phone" v-model="phone" type="tel" inputmode="numeric" autocomplete="tel-national" placeholder="请输入手机号" maxlength="11" /></div>
            <div v-else class="input-wrap"><input id="email" v-model="email" type="email" autocomplete="email" placeholder="请输入邮箱地址" /></div>
          </div>

          <div v-if="needsCode" class="form-field">
            <label for="code">验证码</label>
            <div class="code-row"><div class="input-wrap"><input id="code" v-model="code" type="text" inputmode="numeric" autocomplete="one-time-code" placeholder="请输入验证码" maxlength="6" /></div><button type="button" class="code-button" @click="sendCode">获取验证码</button></div>
          </div>

          <div v-if="needsPassword" class="form-field">
            <label for="password">{{ isRegister ? '设置密码' : '密码' }}</label>
            <div class="input-wrap password-input"><input id="password" v-model="password" :type="showPassword ? 'text' : 'password'" :autocomplete="isRegister ? 'new-password' : 'current-password'" :placeholder="isRegister ? '请设置密码' : '请输入密码'" /><button type="button" :aria-label="showPassword ? '隐藏密码' : '显示密码'" @click="showPassword = !showPassword"><EyeSlash v-if="showPassword" :size="20" /><Eye v-else :size="20" /></button></div>
            <p v-if="isRegister" class="field-hint">至少 8 位，包含字母和数字</p>
          </div>

          <div v-if="isRegister" class="form-check"><label><input v-model="accepted" type="checkbox" /><span>我已阅读并同意</span></label><button type="button" @click="showMessage('用户协议内容将在服务上线前补充。')">《用户协议》</button><span>和</span><button type="button" @click="showMessage('隐私政策内容将在服务上线前补充。')">《隐私政策》</button></div>
          <div v-else class="form-meta"><label><input v-model="remember" type="checkbox" /> 下次自动登录</label><button type="button" @click="setChannel(channel === 'phone' ? 'email' : 'phone')">{{ channel === 'phone' ? '邮箱登录' : '手机号登录' }} <ArrowRight :size="16" /></button></div>

          <p v-if="message" class="form-message" :class="{ 'form-message--error': messageType === 'error' }" role="status" aria-live="polite">{{ message }}</p>

          <button type="submit" class="button button--primary auth-submit">{{ isRegister ? '免费注册并开始创作' : '登录并进入工作台' }} <ArrowRight :size="20" weight="bold" /></button>
        </form>

        <div class="auth-card__switch"><span>{{ isRegister ? '已有账号？' : '还没有账号？' }}</span><RouterLink :to="isRegister ? '/login' : '/register'">{{ isRegister ? '立即登录' : '免费注册' }} <ArrowRight :size="17" /></RouterLink></div>
      </section>
    </div>
  </main>
</template>
