interface IResponse {
  path: string;
}

export async function uploadImage(data: FormData): Promise<IResponse> {
  return await fetch("http://localhost:8080/assets/image", {
    method: "POST",
    credentials: "include",
    // headers: { "Content-Type": "application/form-data" },
    body: data,
  })
    .then((res) => res.json())
    .catch((err) => alert(err));
}
