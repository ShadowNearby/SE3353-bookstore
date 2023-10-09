interface IRequest {
  url: string;
  body: any;
}

interface IGetRequest {
  url: string;
}

export const requestUrl = "http://localhost:8080/";
export const microServiceUrl = "http://localhost:8090/microservice/";

export const getRequest = async ({ url }: IGetRequest) =>
  await fetch(url, {
    method: "GET",
    headers: {
      "Content-Type": "application/json",
    },
    credentials: "include",
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
