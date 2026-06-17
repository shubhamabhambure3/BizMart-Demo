import axiosClient from '../api/axiosClient'

export const createBuyerProfile = async (
  data
) => {

  const response =
    await axiosClient.post(
      '/api/buyers',
      data
    )

  return response.data
}

export const getAllBuyerProfiles =
  async () => {

    const response =
      await axiosClient.get(
        '/api/buyers'
      )

    return response.data
  }

export const updateBuyerProfile =
  async (id, data) => {

    const response =
      await axiosClient.put(
        `/api/buyers/${id}`,
        data
      )

    return response.data
  }

export const deleteBuyerProfile =
  async (id) => {

    await axiosClient.delete(
      `/api/buyers/${id}`
    )
  }