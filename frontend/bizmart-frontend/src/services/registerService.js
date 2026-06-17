import axiosClient from '../api/axiosClient'

export const register = async (registrationRequest) => {
  const response = await axiosClient.post(
    '/api/auth/register',
    registrationRequest
  )

  return response.data
}