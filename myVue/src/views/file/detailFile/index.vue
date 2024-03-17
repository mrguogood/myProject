<template>
  <div>
    <el-card style="max-width: 480px">
      <template #header>
        <div class="card-header">
          <span>文件管理</span>
        </div>
      </template>
      <el-table :data="tableData" style="width: 100%">
        <el-table-column prop="name" label="文件名称" width="180" />
        <el-table-column prop="path" label="图片">
          <template #default="{ row }">
            <img class="_img" :src="row.path" />
          </template>
        </el-table-column>
      </el-table>
    </el-card>
  </div>
</template>

<script lang="ts" setup>
// import { ref, reactive } from 'vue'
import axios from '@/api/file/'
import { onMounted } from 'vue'

onMounted(async () => {
  let { data } = await axios.get('upload/all')
  console.log(data)
  //   tableData.values = data.data
})
const tableData = [
  {
    name: 'Tom',
    path: '/public/logo.png',
  },
]
</script>

<style scoped>
._img {
  width: 100px;
}
</style>
