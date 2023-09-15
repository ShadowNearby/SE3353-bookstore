export interface IBook {
  id: number;
  name: string;
  image: string;
  price: number;
  author: string;
  description: string;
  inventory: number;
  isbn: string;
}

export interface IUser {
  id: number;
  username: string;
  password: string;
  email: string;
  avatar: string;
  role: string;
  registerTime: Date;
  banned: boolean;
}

export interface IReceiver {
  id: number;
  name: string;
  address: string;
  phone: string;
}

export interface IOrderItem {
  id: number;
  book: IBook;
  count: number;
  order: IOrder;
}

export interface IOrder {
  id: number;
  user: IUser;
  receiver: IReceiver;
  orderItemList: IOrderItem[];
  orderTime: Date;
}

export interface IMessage {
  message: string;
}

export interface ICurrentUser {
  id: number;
  username: string;
  avatar: string;
}

export interface ICurrentUserContext {
  user: ICurrentUser;
  updateUser: (user: ICurrentUser) => void;
}
