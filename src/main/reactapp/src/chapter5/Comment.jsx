import React from "react";

import styles from "./Comment.css";
import image from "./image.png";


export default function Comment(props) {
    return (
        <div className="wrapper">
            <div>
                <img src={image} className="image"/>
            </div>
            <div className="contentContainer">
                <span className="nameText">{props.name}</span>
                <span className="commentText">{props.comment}</span>
            </div>
        </div>
    );
}