import React from "react";
import classNames from "classnames";

const Bank = (props) => {
  return (
    <div className="h-100 d-flex justify-content-center align-items-center">
      <div
        className={classNames("p-2 rounded", {
          "btn-success": props.playerNumber == 1,
          "btn-dark": props.playerNumber == 2,
        })}
      >
        <div>
          <h3>{props.noOfMarbles}</h3>
        </div>
        <div className="border-top border-light p-2">
          Bank of Player: {props.playerNumber}
        </div>
      </div>
    </div>
  );
};

export default Bank;
