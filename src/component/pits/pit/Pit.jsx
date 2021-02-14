import React from "react";
import classNames from "classnames";
import "bootstrap/dist/css/bootstrap.min.css";

const Pit = (props) => {
  return (
    <div
      onClick={() => props.clicked(props.pit.id)}
      className={classNames("col-sm m-2 btn p-2 rounded", {
        "btn-success": props.playerNumber == 1,
        "btn-dark": props.playerNumber == 2,
      })}
    >
      <div>
        <h5>{props.pit.noOfMarbles}</h5>
      </div>
      <div className="border-top border-light p-1">Pit: {props.pit.id}</div>
    </div>
  );
};

export default Pit;
