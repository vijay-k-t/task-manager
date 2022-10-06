import React, { useContext, useState } from "react";
import { BiRightArrow } from "react-icons/bi";
import { Context as TaskContext } from "../../context/store/TaskStore";
import "../../css/Task.css";
import {Card, CardGroup} from "react-bootstrap";
const TaskStat = ({ title, stats, variant }) => {
  const [open, setOpen] = useState(true);
 
  return (
       <Card bg={variant.toLowerCase()}
            key={variant}
            text={variant.toLowerCase() === 'light' ? 'dark' : 'white'}
            style={{ width: '10rem', height:"90%" }}
            className="sm-8">
        <Card.Body>
          <Card.Title>{title}</Card.Title>
          <Card.Text>
            {stats}
          </Card.Text>
        </Card.Body>
      </Card>
    
  );
};

export default TaskStat;
