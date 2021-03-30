"use strict";

Object.defineProperty(exports, "__esModule", {
  value: true
});
exports["default"] = void 0;

var _redux = require("redux");

var _backlogReducer = _interopRequireDefault(require("./backlogReducer"));

var _errorReducer = _interopRequireDefault(require("./errorReducer"));

var _projectReducer = _interopRequireDefault(require("./projectReducer"));

var _securityReducer = _interopRequireDefault(require("./securityReducer"));

function _interopRequireDefault(obj) { return obj && obj.__esModule ? obj : { "default": obj }; }

var _default = (0, _redux.combineReducers)({
  errors: _errorReducer["default"],
  project: _projectReducer["default"],
  backlog: _backlogReducer["default"],
  security: _securityReducer["default"]
});

exports["default"] = _default;