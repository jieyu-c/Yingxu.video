<script setup lang="ts">
import mark from '../assets/yingxu-mark-gradient.png'
</script>

<template>
  <RouterLink to="/" class="brand" aria-label="映序AI，返回首页">
    <img :src="mark" alt="" class="brand__mark" />
    <span>映序<span class="brand__ai">AI</span></span>
  </RouterLink>
</template>
