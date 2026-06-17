import axios from 'axios'

const axiosClient = axios.create({
  baseURL: 'https://bizmart-demo.onrender.com',
  headers: {
    'Content-Type': 'application/json'
  }
})

axiosClient.interceptors.request.use(
  (config) => {

    const token = localStorage.getItem('token')

    const publicUrls = [
      '/api/auth/login',
      '/api/auth/register'
    ]

    const isPublicUrl =
      publicUrls.some(url =>
        config.url?.includes(url)
      )

    if (token && !isPublicUrl) {
      config.headers.Authorization =
        `Bearer ${token}`
    }

    return config
  }
)

export default axiosClient