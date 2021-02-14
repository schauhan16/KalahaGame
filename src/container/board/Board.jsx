import React, { Component } from "react";
import "bootstrap/dist/css/bootstrap.min.css";
import Pits from "../../component/pits/Pits";
import Bank from "../../component/bank/Bank";
import axios from "../../axios-move";
import Spinner from "../../component/UI/Spinner/Spinner";

class Board extends Component {
  state = {
    board: null,
    pitsPerPlayer: [1, 2, 3, 4, 5, 6],
    loading: false,
    error: null,
  };

  response = () => {
    const response = {
      activePlayer: 1,
      pits: [],
    };
    for (let index = 1; index <= 14; index++) {
      const pitObject = {
        pit: {
          pitId: index,
          numberOfMarbles: 4,
        },
      };
      response.pits.push(pitObject);
    }
    return response;
  };

  moveMakerHandler = () => {
    // this.setState({ loading: true });
    const move = {
      playerNumber: 1,
      selectedPit: 3,
    };

    axios
      .post("/move", move)
      .then((response) => {
        // console.log(response);
        // this.setState({ loading: false});
      })
      .catch((error) => {
        // console.log(error);
        // this.setState({ loading: false});
      });
  };

  componentDidMount() {
    // this.setState({ board: this.response() });
    axios
      .get("/game")
      .then((response) => {
        let data = {
          activePlayer: response.data.activePlayer,
          pits: response.data.board.pits,
          gameOver: response.data.gameOver,
          winner: response.data.winner,
        };
        this.setState({ board: data });
        this.setState({ loading: false });
      })
      .catch((error) => {
        this.setState({ error: true });
        this.setState({ loading: false });
      });
  }

  getPitsForPlayer1 = () => {
    const board = { ...this.state.board };
    const pits = [...board.pits];
    const pitsForPlayer = [...pits.slice(0, 6)];
    return pitsForPlayer;
  };

  getPitsForPlayer2 = () => {
    const board = { ...this.state.board };
    const pits = [...board.pits];
    const pitsForPlayer = [...pits.slice(7, 13)].reverse();
    return pitsForPlayer;
  };

  getBankForPlayer1 = () => {
    const board = { ...this.state.board };
    const pits = [...board.pits];
    const pitsForPlayer = [...pits.slice(0, 7)];
    return pitsForPlayer[6];
  };

  getBankForPlayer2 = () => {
    const board = { ...this.state.board };
    const pits = [...board.pits];
    const pitsForPlayer = [...pits.slice(7, 14)];
    return pitsForPlayer[6];
  };

  validClick = (pitId) => {
    const activePlayer = this.state.board.activePlayer;
    let pitsForPlayer = null;
    if (1 == activePlayer) {
      pitsForPlayer = this.getPitsForPlayer1();
    } else {
      pitsForPlayer = this.getPitsForPlayer2();
    }
    console.log(pitsForPlayer);
    return pitsForPlayer.filter((pit) => pit.id === pitId).length === 1;
  };

  pitSelectHandler = (pitId) => {
    // if (!this.validClick(pitId)) {
    //alert("Please select pit for player " + this.state.board.activePlayer);
    // return;
    // }
    // console.log("Pit is clicked!!!" + pitId);

    if (this.state.board.gameOver) {
      console.log("Game is over!!!");
      return;
    }

    this.setState({ error: null });
    this.setState({ loading: true });
    axios
      .get("/game/move/" + pitId)
      .then((response) => {
        let data = {
          activePlayer: response.data.activePlayer,
          pits: response.data.board.pits,
          gameOver: response.data.gameOver,
          winner: response.data.winner,
        };
        console.log(response);
        this.setState({ board: data });
        this.setState({ loading: false });
      })
      .catch((error) => {
        this.setState({ error: error.response.data.message });
        this.setState({ loading: false });
      });
  };

  resetGame = () => {
    this.setState({ error: null });
    this.setState({ loading: true });
    axios
      .get("/game/reset/")
      .then((response) => {
        let data = {
          activePlayer: response.data.activePlayer,
          pits: response.data.board.pits,
          gameOver: response.data.gameOver,
          winner: response.data.winner,
        };
        this.setState({ board: data });
        this.setState({ loading: false });
      })
      .catch((error) => {
        if (error.response && error.response.data) {
          this.setState({ error: error.response.data.message });
        } else { 
          this.setState({ error: "Oops!!! Something went wrong." });
        }
        this.setState({ loading: false });
      });
  };

  render() {
    let pitsForPlayer1 = null;
    let pitsForPlayer2 = null;
    let bankForPlayer1 = null;
    let bankForPlayer2 = null;

    let board = null;
    // console.log(this.state.board.pits);
    if (this.state.board) {
      pitsForPlayer1 = this.getPitsForPlayer1();
      pitsForPlayer2 = this.getPitsForPlayer2();

      bankForPlayer1 = this.getBankForPlayer1();
      bankForPlayer2 = this.getBankForPlayer2();

      board = (
        <div className="row">
          <div className="col-sm">
            <Bank playerNumber="2" noOfMarbles={bankForPlayer2.noOfMarbles} />
          </div>
          <div className="col-8">
            <Pits
              playerNumber="2"
              pitsPerPlayer={pitsForPlayer2}
              onClickHandler={this.pitSelectHandler}
            />
            <Pits
              playerNumber="1"
              pitsPerPlayer={pitsForPlayer1}
              onClickHandler={this.pitSelectHandler}
            />
          </div>
          <div className="col-sm">
            <Bank
              playerNumber="1"
              noOfMarbles={bankForPlayer1.noOfMarbles}
              className="col-sm"
            />
          </div>
        </div>
      );
    }
    if (this.state.loading) {
      board = <Spinner />;
    }

    let error = null;
    if (this.state.error) {
      error = <div className="alert alert-danger">{this.state.error}</div>;
    }

    let info = null;
    console.log(this.state.board);
    if (this.state.board && this.state.board.gameOver) {
      let message = "Player " + this.state.board.winner + " won.";
      if (this.state.board.winner == 0) {
        message = "It's a tie";
      }
      info = <div className="alert alert-success">Game over. {message}</div>;
    }

    return (
      <div className="shadow p-3 mb-5 bg-white rounded">
        <h4>Kalaha Board</h4>
        {error}
        {info}
        <div className="container"></div>
        {board}
        <div className="d-flex flex-row-reverse">
          <div className="btn btn-danger" onClick={this.resetGame}>
            Reset Game
          </div>
        </div>
      </div>
    );
  }
}

export default Board;
