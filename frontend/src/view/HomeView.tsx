import React, { useContext, useEffect, useState } from "react";
import { ArcElement, Chart, Legend, Tooltip } from "chart.js";
import moment from "moment";
import { PersonalChart } from "../compoents/chart/PersonalChart";
import { UserContext } from "../context/UserProvider";
import { ICurrentUserContext } from "../interface";

Chart.register(ArcElement, Tooltip, Legend);
// const pictureData = [
//   "http://img61.ddimg.cn/upload_img/00838/cxtc/750x315_gr_0322-1679479215.jpg",
//   "http://img60.ddimg.cn/2022/4/13/2022041316363321714.png",
//   "http://img62.ddimg.cn/ddimg/2508/750X315-1679036508.jpg",
// ];
export const HomeView = () => {
  const [helloMessage, setHelloMessage] = useState("");
  const { user } = useContext(UserContext) as ICurrentUserContext;
  useEffect(() => {
    const time = moment(new Date()).hour();
    let timeString: string;
    let username = user.username;
    if (username == null) username = "";
    if (time > 0 && time <= 5) {
      timeString = "夜深了 该休息了哦!";
    } else if (time <= 9 && time > 5) {
      timeString = "美好的一天，从读书开始吧！";
    } else if (time > 9 && time <= 11) {
      timeString = "上午好！" + username;
    } else if (time > 11 && time <= 15) {
      timeString = "下午好！" + username;
    } else if (time > 15 && time < 19) {
      timeString = "下午好！" + username;
    } else {
      timeString = "忙碌了一天，来看书吧！";
    }
    setHelloMessage(timeString);
  }, []);
  return (
    <>
      <h1 style={{ justifyContent: "center", fontSize: 30, marginLeft: 30 }}>
        {helloMessage}
      </h1>

      {/*<Carousel autoplay style={{ justifyContent: "center" }}>*/}
      {/*  <div>*/}
      {/*    <Image src={pictureData[0]} preview={false} />*/}
      {/*  </div>*/}
      {/*  <div>*/}
      {/*    <Image src={pictureData[1]} preview={false} />*/}
      {/*  </div>*/}
      {/*  <div>*/}
      {/*    <Image src={pictureData[2]} preview={false} />*/}
      {/*  </div>*/}
      {/*</Carousel>*/}
      <div style={{ display: "flex", marginTop: 30, justifyContent: "center" }}>
        <PersonalChart />
      </div>
    </>
  );
};
