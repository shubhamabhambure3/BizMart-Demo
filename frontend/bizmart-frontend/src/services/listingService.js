import axiosClient from '../api/axiosClient'

export const createListing = async (data) => {
  const response = await axiosClient.post(
    '/api/listings',
    data
  )

  return response.data
}

export const getAllListings = async () => {
  const response = await axiosClient.get(
    '/api/listings'
  )

  return response.data
}

export const updateListing = async (
  id,
  data
) => {
  const response = await axiosClient.put(
    `/api/listings/${id}`,
    data
  )

  return response.data
}

export const deleteListing = async (id) => {
  await axiosClient.delete(
    `/api/listings/${id}`
  )
}