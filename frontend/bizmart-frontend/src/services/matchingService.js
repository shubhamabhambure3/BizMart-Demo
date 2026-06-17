import axiosClient from '../api/axiosClient'

export const getMatchingListings =
  async (buyerProfileId) => {

    const response =
      await axiosClient.get(
        `/api/matching/${buyerProfileId}`
      )

    return response.data
  }