interface IRequest {
  url: string;
  body: any;
}

export const requestUrl = "http://localhost:8080/";

export const getRequest = async ({ url, body }: IRequest) =>
  await fetch(url, {
    method: "GET",
    headers: {
      "Content-Type": "application/json",
    },
    credentials: "include",
    body: JSON.stringify(body),
  });

export const deleteRequest = async ({ url, body }: IRequest) =>
  await fetch(url, {
    method: "DELETE",
    headers: {
      "Content-Type": "application/json",
    },
    credentials: "include",
    body: JSON.stringify(body),
  });

export const putRequest = async ({ url, body }: IRequest) =>
  await fetch(url, {
    method: "PUT",
    headers: {
      "Content-Type": "application/json",
    },
    credentials: "include",
    body: JSON.stringify(body),
  });

export const postRequest = async ({ url, body }: IRequest) =>
  await fetch(url, {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    credentials: "include",
    body: JSON.stringify(body),
  });
