import axiosClient from '../api/axiosClient'

export const createContact = async (
  data
) => {

  const response =
    await axiosClient.post(
      '/api/contacts',
      data
    )

  return response.data
}

export const getAllContacts =
  async () => {

    const response =
      await axiosClient.get(
        '/api/contacts'
      )

    return response.data
  }

export const updateContactStatus =
  async (
    id,
    status
  ) => {

    const response =
      await axiosClient.put(
        `/api/contacts/${id}?status=${status}`
      )

    return response.data
  }

export const deleteContact =
  async (id) => {

    await axiosClient.delete(
      `/api/contacts/${id}`
    )
  }