<script setup lang="ts">
import { computed, ref } from 'vue'
import { PhPlay as Play, PhPause as Pause } from '@phosphor-icons/vue'
import travelPoster from '../assets/coastal-hero.png'
import productPoster from '../assets/demo-product.png'
import storyPoster from '../assets/demo-story.png'

type Demo = { id: string; title: string; description: string; poster: string; src: string }

// Add licensed finished-video URLs here when they are available.
const demos: Demo[] = [
  { id: 'travel', title: '旅行短片', description: '把旅途中的温柔与惊喜，变成一支会讲故事的影片。', poster: travelPoster, src: '' },
  { id: 'product', title: '产品广告', description: '让产品的质感与故事，在每一个镜头里自然呈现。', poster: productPoster, src: '' },
  { id: 'story', title: '剧情氛围', description: '用画面、音乐与节奏，留住触动人心的情绪。', poster: storyPoster, src: '' },
]

const selectedIndex = ref(0)
const selected = computed(() => demos[selectedIndex.value]!)
const playing = ref(false)
const video = ref<HTMLVideoElement | null>(null)

function selectDemo(index: number) {
  video.value?.pause()
  playing.value = false
  selectedIndex.value = index
}

async function togglePlayback() {
  if (!selected.value.src || !video.value) return
  if (video.value.paused) {
    await video.value.play()
    playing.value = true
  } else {
    video.value.pause()
    playing.value = false
  }
}
</script>

<template>
  <section id="examples" class="theater" aria-labelledby="theater-title">
    <div class="page-width">
      <div class="theater__heading">
        <p class="eyebrow">AI 影像创作 · 灵感放映厅</p>
        <h2 id="theater-title">不同的故事，<span>同样精彩</span></h2>
        <p>从灵感到成片，探索映序AI的视频创作方式。</p>
      </div>

      <div class="theater__stage" :style="{ backgroundImage: `url(${selected.poster})` }">
        <video v-if="selected.src" ref="video" :key="selected.id" :src="selected.src" :poster="selected.poster" playsinline preload="metadata" controls @play="playing = true" @pause="playing = false" @ended="playing = false" />
        <div v-if="!playing" class="theater__stage-shade"></div>
        <div v-if="!playing" class="theater__stage-copy">
          <span class="theater__stage-index">0{{ selectedIndex + 1 }} / 0{{ demos.length }}</span>
          <h3>{{ selected.title }}</h3>
          <p>{{ selected.description }}</p>
          <button v-if="selected.src" type="button" class="theater__play" @click="togglePlayback">观看成片 <Play :size="16" weight="fill" /></button>
          <span v-else class="theater__pending">成片视频待接入</span>
        </div>
        <button v-if="selected.src && playing" type="button" class="theater__pause" aria-label="暂停视频" @click="togglePlayback"><Pause :size="18" weight="fill" /></button>
      </div>

      <div class="theater__rail" aria-label="选择成片演示">
        <button v-for="(demo, index) in demos" :key="demo.id" type="button" class="theater__choice" :class="{ 'is-active': selectedIndex === index }" :aria-pressed="selectedIndex === index" @click="selectDemo(index)">
          <span class="theater__choice-number">0{{ index + 1 }}</span>
          <span class="theater__choice-copy"><strong>{{ demo.title }}</strong><small>{{ demo.description }}</small></span>
          <img :src="demo.poster" :alt="`${demo.title}封面`" />
        </button>
      </div>
      <p class="theater__note">以上为作品类型展示，真实成片将在素材就绪后接入。</p>
    </div>
  </section>
</template>
