import { BrowserRouter, Route, Routes } from "react-router-dom";
import React from "react";
import { HomeView } from "../view/HomeView";
import { BooksView } from "../view/BooksView";
import { LoginView } from "../view/LoginView";
import { AdminOrdersView } from "../view/admin/AdminOrdersView";
import { AdminChartView } from "../view/admin/AdminChartView";
import { AdminUsersView } from "../view/admin/AdminUsersView";
import { OrderView } from "../view/OrderView";
import { UserView } from "../view/UserView";
import { RegisterView } from "../view/RegisterView";
import { CartView } from "../view/CartView";
import { AdminBooksView } from "../view/admin/AdminBooksView";
import { BookDetailsView } from "../view/BookDetailsView";
import { ForgetView } from "../view/ForgetView";
import { LayoutView } from "../view/LayoutView";
import { AdminLayout } from "../view/admin/AdminLayout";

export const Router = () => {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/login" element={<LoginView />}></Route>
        <Route path="/register" element={<RegisterView />}></Route>
        <Route path="/forget" element={<ForgetView />}></Route>
        <Route path="" element={<LayoutView />}>
          <Route path={"/"} element={<HomeView />}></Route>
          <Route path="/books" element={<BooksView />}></Route>
          <Route path="/user" element={<UserView />}></Route>
          <Route path="/cart" element={<CartView />}></Route>
          <Route path="/order" element={<OrderView />}></Route>
          <Route path="/book/:id" element={<BookDetailsView />}></Route>
        </Route>
        <Route path={"/admin"} element={<AdminLayout />}>
          <Route path="/admin/users" element={<AdminUsersView />}></Route>
          <Route path="/admin/books" element={<AdminBooksView />}></Route>
          <Route path="/admin/orders" element={<AdminOrdersView />}></Route>
          <Route path="/admin/chart" element={<AdminChartView />}></Route>
        </Route>
      </Routes>
    </BrowserRouter>
  );
};
