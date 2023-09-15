import React from "react";
import {FloatButton, Layout, Space} from "antd";
import {FacebookOutlined, InstagramOutlined, QqOutlined, TwitterOutlined, WechatOutlined} from "@ant-design/icons";

const {Footer} = Layout;
export const FootBar = () => {
    return (
        <Footer style={{textAlign: "center"}}>
            <FloatButton.BackTop/>
            <Space>
                <span>{"Book Store From SJTU SE"}</span>
                <QqOutlined/>
                <WechatOutlined/>
                <FacebookOutlined/>
                <InstagramOutlined/>
                <TwitterOutlined/>
            </Space>
        </Footer>
    );
};
