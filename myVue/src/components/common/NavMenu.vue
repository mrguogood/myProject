<template>
  <div>
    <el-menu
      :default-active="currentPath"
      router
      mode="horizontal"
      background-color="white"
      text-color="#222"
      active-text-color="red"
      style="min-width: 1300px">

      <span style="position: absolute;padding-top: 20px;right: 43%;font-size: 20px;font-weight: bold">归零</span>
      <el-menu-item v-for="(item,i) in navList" :key="i" :index="item.name">
        {{ item.name }}
      </el-menu-item>
      <el-input
        placeholder="快速搜索..."
        prefix-icon="el-icon-search"
        size="medium"
        style="width: 300px;position:absolute;margin-top: 12px;right: 18%"
        v-model="keywords">
      </el-input>
    </el-menu>
  </div>
</template>

<script setup lang="ts" name="NavMenu">

import {reactive,ref} from 'vue'


// 数据
let navList = reactive([
  {id:'aysdytfsatr01',name:'王者荣耀'},
  {id:'aysdytfsatr02',name:'原神'},
  {id:'aysdytfsatr03',name:'三国志'}
])

let keywords = ref('张三')

</script>

<style scoped>
  a{
    text-decoration: none;
  }

  span {
    pointer-events: none;
  }

</style>
