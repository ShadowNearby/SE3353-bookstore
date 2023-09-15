import { uploadImage } from "../../service/upload/uploadImage";
import { LoadingOutlined } from "@ant-design/icons";
import { Avatar, message, Upload } from "antd";
import React, { Dispatch, SetStateAction, useState } from "react";
import { RcFile } from "antd/es/upload";

interface Props {
  state: string;
  setState: Dispatch<SetStateAction<string>>;
}

export const UploadImage = ({ state, setState }: Props) => {
  const [uploading, setUploading] = useState(false);
  const beforeUpload = (file: RcFile) => {
    const isJpgOrPng = file.type === "image/png";
    if (!isJpgOrPng) {
      message.error("You can only upload PNG file!", 1).then();
    }
    const isLt2M = file.size / 1024 / 1024 < 2;
    if (!isLt2M) {
      message.error("Image must smaller than 2MB!", 1).then();
    }
    return isJpgOrPng && isLt2M;
  };
  const handleUploadAction = async (file: RcFile) => {
    setUploading(true);
    const formData = new FormData();
    formData.append("file", file);
    await uploadImage(formData).then((res) => {
      setState(res.path);
      setUploading(false);
    });
    return "";
  };
  return (
    <Upload
      beforeUpload={beforeUpload}
      showUploadList={false}
      action={handleUploadAction}
      style={{ width: 120, height: 120 }}
    >
      {uploading ? (
        <LoadingOutlined />
      ) : (
        <Avatar
          src={state}
          style={{ marginTop: 16, width: 100, height: 100 }}
        />
      )}
    </Upload>
  );
};
