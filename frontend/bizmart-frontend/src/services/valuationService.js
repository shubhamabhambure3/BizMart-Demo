import axiosClient from '../api/axiosClient'

export const generateValuation =
  async (companyId) => {

    const response =
      await axiosClient.post(
        '/api/valuations',
        {
          companyId
        }
      )

    return response.data
  }

export const getValuationByCompanyId =
  async (companyId) => {

    const response =
      await axiosClient.get(
        `/api/valuations/company/${companyId}`
      )

    return response.data
  }

  export const getAllValuations =
  async () => {

    const response =
      await axiosClient.get(
        '/api/valuations'
      )

    return response.data
  }