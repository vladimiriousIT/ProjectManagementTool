"use strict";

Object.defineProperty(exports, "__esModule", {
  value: true
});
exports["default"] = void 0;

var _axios = _interopRequireDefault(require("axios"));

function _interopRequireDefault(obj) { return obj && obj.__esModule ? obj : { "default": obj }; }

var setJWTToken = function setJWTToken(token) {
  if (token) {
    _axios["default"].defaults.headers.common["Authorization"] = token;
  } else {
    delete _axios["default"].defaults.headers.common["Authorization"];
  }
};

var _default = setJWTToken;
exports["default"] = _default;