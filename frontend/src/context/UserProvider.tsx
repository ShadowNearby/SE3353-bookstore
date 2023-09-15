import { createContext, useState } from "react";
import { ICurrentUser, ICurrentUserContext } from "../interface";

export const UserContext = createContext<ICurrentUserContext | null>(null);

// @ts-ignore
export const UserProvider = ({ children }) => {
  const [user, setUser] = useState<ICurrentUser>({
    id: 0,
    username: "",
    avatar: "",
  });
  const updateUser = (user: ICurrentUser) => {
    setUser(user);
  };
  return (
    <UserContext.Provider value={{ user, updateUser }}>
      {children}
    </UserContext.Provider>
  );
};
