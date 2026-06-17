import axiosClient from '../api/axiosClient'

export const createInterest = async (
  interestData
) => {

  const response =
    await axiosClient.post(
      '/api/interests',
      interestData
    )

  return response.data
}

export const getAllInterests = async () => {

  const response =
    await axiosClient.get(
      '/api/interests'
    )

  return response.data
}

export const deleteInterest = async (
  id
) => {

  const response =
    await axiosClient.delete(
      `/api/interests/${id}`
    )

  return response.data
}

export const updateInterest = async (
  id,
  interestData
) => {

  const response =
    await axiosClient.put(
      `/api/interests/${id}`,
      interestData
    )

  return response.data
}

export const getInterestByBuyer = async (
  buyerProfileId
) => {

  const response =
    await axiosClient.get(
      `/api/interests/buyer/${buyerProfileId}`
    )

  return response.data
}

export const getInterestByListing = async (
  listingId
) => {

  const response =
    await axiosClient.get(
      `/api/interests/listing/${listingId}`
    )

  return response.data
}