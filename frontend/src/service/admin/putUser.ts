interface Props {
  id: number;
  username: string;
  avatar: string;
  email: string;
  registerTime: Date;
  role: string;
  banned: boolean;
}

export async function putUser({
  id,
  username,
  avatar,
  email,
  role,
  registerTime,
  banned,
}: Props) {
  return await fetch("http://localhost:8080/api/user/put", {
    method: "PUT",
    credentials: "include",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify({
      id: id,
      username: username,
      avatar: avatar,
      email: email,
      role: role,
      registerTime: registerTime,
      banned: banned,
    }),
  })
    .then((res) => res.json())
    .catch((err) => alert(err));
}
