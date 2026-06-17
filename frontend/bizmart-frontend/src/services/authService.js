import axiosClient from '../api/axiosClient'

export const login = async (loginRequest) => {
  const response = await axiosClient.post(
    '/api/auth/login',
    loginRequest
  )

  return response.data
}

export const saveToken = (token) => {
  localStorage.setItem('token', token)
}

export const getToken = () => {
  return localStorage.getItem('token')
}

export const logout = () => {
  localStorage.removeItem('token')
}