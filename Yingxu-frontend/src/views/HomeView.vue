<script setup lang="ts">
import { ref } from 'vue'
import {
  PhArrowRight as ArrowRight,
  PhChatCircleDots as ChatCircleDots,
  PhFilmStrip as FilmStrip,
  PhPlay as Play,
  PhScissors as Scissors,
  PhVideoCamera as VideoCamera,
} from '@phosphor-icons/vue'
import BrandLogo from '../components/BrandLogo.vue'
import ShowcaseTheater from '../components/ShowcaseTheater.vue'
import hero from '../assets/coastal-hero.png'
import heroHandwriting from '../assets/hero-handwriting.png'
import filmRibbon from '../assets/film-ribbon.png'
import lifestylePoster from '../assets/gallery-lifestyle.png'
import mountainPoster from '../assets/gallery-mountain.png'
import dogPoster from '../assets/gallery-dog.png'
import productPoster from '../assets/demo-product.png'
import storyPoster from '../assets/demo-story.png'

const menuOpen = ref(false)

const features = [
  { icon: ChatCircleDots, title: '自然语言创作', description: '像聊天一样描述想法，开启视频创作。' },
  { icon: VideoCamera, title: '电影级画面', description: '让灵感呈现为有氛围、有细节的镜头。' },
  { icon: Scissors, title: '对话式剪辑', description: '用一句话调整镜头、字幕和节奏。' },
]

const steps = [
  { number: '01', icon: ChatCircleDots, title: '输入创意', description: '描述主题、画面和想表达的故事。' },
  { number: '02', icon: FilmStrip, title: '生成画面', description: '将想法转化为连续的分镜和视频片段。' },
  { number: '03', icon: Scissors, title: '对话剪辑', description: '让 AI 协助修改，完成属于你的成片。' },
]

const faqs = [
  { question: '需要有剪辑经验才能使用吗？', answer: '不需要。你可以先用自然语言描述想法，再逐步调整生成的镜头和视频节奏。' },
  { question: '可以自己修改生成的视频吗？', answer: '可以。映序AI既支持对话式修改，也规划了可自主操作的专业编辑模式。' },
  { question: '什么时候可以开始体验？', answer: '产品正在持续开发中。你可以先注册账号，后续功能开放会在产品内通知。' },
]

const openedFaq = ref<number | null>(0)

const inspirationStories = [
  { title: '生活的美好，值得被记录', category: '生活影像', image: lifestylePoster, className: 'inspiration-card--lead' },
  { title: '去看更大的世界', category: '旅行纪实', image: mountainPoster, className: 'inspiration-card--mountain' },
  { title: '让好物更有故事', category: '产品广告', image: productPoster, className: 'inspiration-card--product' },
  { title: '每一次奔跑，都是快乐', category: '萌宠日常', image: dogPoster, className: 'inspiration-card--dog' },
  { title: '光影里，有另一种自己', category: '剧情氛围', image: storyPoster, className: 'inspiration-card--story' },
]

function closeMenu() {
  menuOpen.value = false
}
</script>

<template>
  <main>
    <section class="hero" :style="{ backgroundImage: `url(${hero})` }">
      <header class="site-header page-width">
        <BrandLogo />
        <button
          class="menu-toggle icon-button"
          type="button"
          :aria-expanded="menuOpen"
          aria-label="切换导航菜单"
          @click="menuOpen = !menuOpen"
        >
          <span></span><span></span><span></span>
        </button>
        <nav class="main-nav" :class="{ 'main-nav--open': menuOpen }" aria-label="主导航">
          <a href="#features" @click="closeMenu">产品功能</a>
          <a href="#examples" @click="closeMenu">创作案例</a>
          <a href="#pricing" @click="closeMenu">价格方案</a>
          <a href="#help" @click="closeMenu">帮助中心</a>
        </nav>
        <div class="header-actions">
          <RouterLink to="/login" class="header-login">登录</RouterLink>
          <RouterLink to="/register" class="button button--primary button--small">开始创作 <ArrowRight :size="18" weight="bold" /></RouterLink>
        </div>
      </header>

      <div class="hero__content page-width">
        <h1>一句灵感，<br />生成一支<span>好视频</span></h1>
        <p class="hero__subtitle">从脚本、画面到剪辑，AI 全程协作</p>
        <div class="hero__actions">
          <RouterLink to="/register" class="button button--primary button--large">立即创作 <ArrowRight :size="20" weight="bold" /></RouterLink>
          <a href="#examples" class="button button--light button--large"><Play :size="20" weight="fill" /> 观看案例</a>
        </div>
        <div class="hero__features" aria-label="产品特点">
          <div v-for="feature in features" :key="feature.title" class="hero-feature">
            <span class="hero-feature__icon"><component :is="feature.icon" :size="24" weight="duotone" /></span>
            <span><strong>{{ feature.title }}</strong><small>{{ feature.description }}</small></span>
          </div>
        </div>
      </div>
      <img :src="heroHandwriting" alt="让每一个灵感，都有一部自己的电影" class="hero__handwriting" />
      <img :src="filmRibbon" alt="" class="hero__ribbon" />
    </section>

    <div class="home-atmosphere">
    <section id="features" class="journey section-pad">
      <div class="page-width">
        <h2>只需三步，让你的创意变成完整视频</h2>
        <div class="steps">
          <template v-for="(step, index) in steps" :key="step.number">
            <div class="step">
              <span class="step__number">{{ step.number }}</span>
              <component :is="step.icon" :size="30" weight="duotone" class="step__icon" />
              <div><h3>{{ step.title }}</h3><p>{{ step.description }}</p></div>
            </div>
            <ArrowRight v-if="index < steps.length - 1" class="step-arrow" :size="22" />
          </template>
        </div>
      </div>
    </section>

    <ShowcaseTheater />

    <section class="inspiration" aria-labelledby="inspiration-title">
      <div class="page-width">
        <div class="inspiration__heading">
          <p class="eyebrow">更多创意 · 正在被实现</p>
          <h2 id="inspiration-title">从灵感到成片，激发每一种可能</h2>
          <p>无论是生活记录、品牌故事，还是创意短片，映序AI都能帮你轻松完成。</p>
        </div>
        <div class="inspiration__grid">
          <article v-for="story in inspirationStories" :key="story.title" class="inspiration-card" :class="story.className">
            <img :src="story.image" :alt="story.category + '灵感封面'" loading="lazy" />
            <div class="inspiration-card__shade"></div>
            <div class="inspiration-card__copy"><h3>{{ story.title }}</h3></div>
          </article>
        </div>
        <p class="inspiration__note">以上为创意灵感封面，成片视频将在素材就绪后接入。</p>
      </div>
    </section>

    <section id="pricing" class="pricing">
      <div class="page-width pricing__inner">
        <div><h2>现在，让你的灵感成为一支好视频</h2><p>映序AI · 让创作更简单，也更有想象力</p></div>
        <RouterLink to="/register" class="button button--primary button--large">立即创作 <ArrowRight :size="20" weight="bold" /></RouterLink>
        <span class="pricing__signature" aria-hidden="true">Your Story,<br />In Motion.</span>
      </div>
    </section>
    </div>

    <section id="help" class="faq section-pad">
      <div class="page-width faq__grid"><div><p class="eyebrow section-eyebrow">FAQ</p><h2>常见问题</h2><p>关于用映序AI创作视频，你可能想先了解这些。</p></div>
        <div class="faq__list"><div v-for="(item, index) in faqs" :key="item.question" class="faq__item"><button type="button" :aria-expanded="openedFaq === index" @click="openedFaq = openedFaq === index ? null : index"><span>{{ item.question }}</span><span class="faq__plus">{{ openedFaq === index ? '−' : '+' }}</span></button><p v-if="openedFaq === index">{{ item.answer }}</p></div></div>
      </div>
    </section>

    <footer class="site-footer"><div class="page-width site-footer__inner"><BrandLogo /><p>让每一个灵感，都有一部自己的电影。</p><span>© {{ new Date().getFullYear() }} 映序AI</span></div></footer>
  </main>
</template>
