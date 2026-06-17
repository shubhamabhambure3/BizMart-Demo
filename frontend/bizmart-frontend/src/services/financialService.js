import axiosClient from '../api/axiosClient'

export const createFinancial = async (data) => {
  const response = await axiosClient.post(
    '/api/financials',
    data
  )

  return response.data
}

export const getAllFinancials = async () => {
  const response = await axiosClient.get(
    '/api/financials'
  )

  return response.data
}

export const updateFinancial = async (
  id,
  data
) => {
  const response = await axiosClient.put(
    `/api/financials/${id}`,
    data
  )

  return response.data
}

export const deleteFinancial = async (id) => {
  await axiosClient.delete(
    `/api/financials/${id}`
  )
}