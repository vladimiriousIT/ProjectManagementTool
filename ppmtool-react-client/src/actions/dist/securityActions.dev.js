"use strict";

Object.defineProperty(exports, "__esModule", {
  value: true
});
exports.logout = exports.login = exports.createNewUser = void 0;

var _axios = _interopRequireDefault(require("axios"));

var _types = require("./types");

var _setJWTToken = _interopRequireDefault(require("../securityUtils/setJWTToken"));

var _jwtDecode = _interopRequireDefault(require("jwt-decode"));

function _interopRequireDefault(obj) { return obj && obj.__esModule ? obj : { "default": obj }; }

var createNewUser = function createNewUser(newUser, history) {
  return function _callee(dispatch) {
    return regeneratorRuntime.async(function _callee$(_context) {
      while (1) {
        switch (_context.prev = _context.next) {
          case 0:
            _context.prev = 0;
            _context.next = 3;
            return regeneratorRuntime.awrap(_axios["default"].post("http://localhost:8080/api/users/register", newUser));

          case 3:
            history.push("/login");
            dispatch({
              type: _types.GET_ERRORS,
              payload: {}
            });
            _context.next = 10;
            break;

          case 7:
            _context.prev = 7;
            _context.t0 = _context["catch"](0);
            dispatch({
              type: _types.GET_ERRORS,
              payload: _context.t0.response.data
            });

          case 10:
          case "end":
            return _context.stop();
        }
      }
    }, null, null, [[0, 7]]);
  };
};

exports.createNewUser = createNewUser;

var login = function login(LoginRequest) {
  return function _callee2(dispatch) {
    var res, token, decoded;
    return regeneratorRuntime.async(function _callee2$(_context2) {
      while (1) {
        switch (_context2.prev = _context2.next) {
          case 0:
            _context2.prev = 0;
            _context2.next = 3;
            return regeneratorRuntime.awrap(_axios["default"].post("http://localhost:8080/api/users/login", LoginRequest));

          case 3:
            res = _context2.sent;
            // extract token from res.data
            token = res.data.token; // store the token in the localStorage

            localStorage.setItem("jwtToken", token); // set our token in header ***

            (0, _setJWTToken["default"])(token); // decode token on React

            decoded = (0, _jwtDecode["default"])(token); // dispatch to our securityReducer

            dispatch({
              type: _types.SET_CURRENT_USER,
              payload: decoded
            });
            _context2.next = 14;
            break;

          case 11:
            _context2.prev = 11;
            _context2.t0 = _context2["catch"](0);
            dispatch({
              type: _types.GET_ERRORS,
              payload: _context2.t0.response.data
            });

          case 14:
          case "end":
            return _context2.stop();
        }
      }
    }, null, null, [[0, 11]]);
  };
};

exports.login = login;

var logout = function logout() {
  return function (dispatch) {
    localStorage.removeItem("jwtToken");
    (0, _setJWTToken["default"])(false);
    dispatch({
      type: _types.SET_CURRENT_USER,
      payload: {}
    });
  };
};

exports.logout = logout;