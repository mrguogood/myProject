<template>
  <div>
    <el-card style="max-width: 480px">
      <template #header>
        <div class="card-header">
          <span>多文件上传</span>
        </div>
      </template>
      <el-form :model="form" label-width="auto" style="max-width: 600px">
        <el-form-item label="文件名称">
          <el-input v-model="form.name" />
        </el-form-item>
        <el-form-item label="上传文件">
          <el-upload
            v-model:file-list="fileList"
            class="upload-demo"
            action=""
            :http-request="uploadFile"
            :limit="2"
            multiple
            :on-exceed="uploadExceed"
            :before-upload="beforeUpload"
            :on-remove="uploadRemove"
            :on-preview="uploadRreview"
            accept=".jpg"
            list-type="picture"
            :on-change="handleChange"
          >
            <el-button type="primary">上传文件</el-button>
            <template #tip>
              <div class="el-upload__tip">jpg/png 文件不大于20M!</div>
            </template>
          </el-upload>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="onSubmit">提交</el-button>
        </el-form-item>
      </el-form>
    </el-card>
  </div>
</template>

<script setup lang="ts">
import { ref, reactive } from 'vue'
import axios from '@/api/file/'
import { ElMessageBox, ElMessage, ElNotification } from 'element-plus'
import { useRouter } from 'vue-router'

const router = useRouter()
const form = reactive({
  name: '',
  file: '',
})
const fileList = ref()

//覆盖默认的xhr行为，允许自行实现上传文件的请求
const uploadFile = () => {
  // form.file = v.file
}
const uploadExceed = () => {
  ElMessage.warning('已超出最大文件数！')
}
const beforeUpload = (file: { type: string; size: number }) => {
  const isJPG = file.type === 'image/jpeg'
  if (!isJPG) {
    ElMessage.warning('上传的格式只能是JPG！')
  }
  const limitSize = file.size / 1024 / 1024 < 20
  if (!limitSize) {
    ElMessage.warning('上传的大小不能超过20M！')
  }
  return isJPG && limitSize
}
const uploadRemove = () => {
  ElMessageBox({
    title: '提示',
    message: '确定删除该文件?',
  })
}
const uploadRreview = () => {
  ElMessageBox({
    title: '提示',
    message: '是否操作?',
  })
}
const handleChange = (fileList: any) => {
  form.file = fileList
}
const onSubmit = async () => {
  console.log(form)
  let formData = new FormData()
  formData.append('name', form.name)
  // formData.append('file', form.file)
  for (let i = 0; i < form.file.length; i++) {
    formData.append('file', form.file[i])
  }

  let { data } = await axios.post('/upload', formData, {
    headers: { 'Content-type': 'multipart/form-data' },
  })
  if (data.success) {
    ElNotification({
      title: '上传成功',
      message: '点赞',
      duration: 2000,
      type: 'success',
    })
    router.push('/')
  }
  console.log(data)
}
</script>

<style></style>
