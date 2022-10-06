import React, { useEffect, useState } from "react";
import "../../css/Navbar.css";
import apiServer from "../../config/apiServer";

const UserAvatar = ({ id }) => {
  const [user, setUser] = useState();
  const [loading, setLoading] = useState(true);

  const getUser = async () => {
    const res = await apiServer.get(`/account/findById/${id}`);
    setUser(res.data);
    setLoading(false);
  };

  useEffect(() => {
    getUser();
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, []);

  if (loading) {
    return <div>Loading..</div>;
  }
  return (
    <div className="user-avatar">
      
    </div>
  );
};

export default UserAvatar;
