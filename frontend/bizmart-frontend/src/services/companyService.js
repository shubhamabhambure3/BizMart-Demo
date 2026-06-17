import axiosClient from '../api/axiosClient'

export const createCompany = async (companyData) => {
  const response = await axiosClient.post(
    '/api/companies',
    companyData
  )

  return response.data
}

export const getAllCompanies = async () => {
  const response = await axiosClient.get(
    '/api/companies'
  )

  return response.data
}

export const deleteCompany = async (id) => {
  const response = await axiosClient.delete(
    `/api/companies/${id}`
  )

  return response.data
}

export const updateCompany = async (
  id,
  companyData
) => {

  const response =
    await axiosClient.put(
      `/api/companies/${id}`,
      companyData
    )

  return response.data
}