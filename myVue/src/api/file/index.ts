import axios from 'axios'
axios.defaults.baseURL = 'http://localhost:6001/export'
axios.interceptors.request.use((config) => config)
axios.interceptors.response.use(
  (res) => res,
  (err) => Promise.reject(err),
)
export default axios
