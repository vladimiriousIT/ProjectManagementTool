"use strict";

Object.defineProperty(exports, "__esModule", {
  value: true
});
exports["default"] = projectReducerFn;

var _types = require("../actions/types");

function ownKeys(object, enumerableOnly) { var keys = Object.keys(object); if (Object.getOwnPropertySymbols) { var symbols = Object.getOwnPropertySymbols(object); if (enumerableOnly) symbols = symbols.filter(function (sym) { return Object.getOwnPropertyDescriptor(object, sym).enumerable; }); keys.push.apply(keys, symbols); } return keys; }

function _objectSpread(target) { for (var i = 1; i < arguments.length; i++) { var source = arguments[i] != null ? arguments[i] : {}; if (i % 2) { ownKeys(source, true).forEach(function (key) { _defineProperty(target, key, source[key]); }); } else if (Object.getOwnPropertyDescriptors) { Object.defineProperties(target, Object.getOwnPropertyDescriptors(source)); } else { ownKeys(source).forEach(function (key) { Object.defineProperty(target, key, Object.getOwnPropertyDescriptor(source, key)); }); } } return target; }

function _defineProperty(obj, key, value) { if (key in obj) { Object.defineProperty(obj, key, { value: value, enumerable: true, configurable: true, writable: true }); } else { obj[key] = value; } return obj; }

var initialState = {
  projects: [],
  project: {}
};

function projectReducerFn() {
  var state = arguments.length > 0 && arguments[0] !== undefined ? arguments[0] : initialState;
  var action = arguments.length > 1 ? arguments[1] : undefined;

  switch (action.type) {
    case _types.GET_PROJECTS:
      return _objectSpread({}, state, {
        projects: action.payload
      });

    case _types.GET_PROJECT:
      {
        return _objectSpread({}, state, {
          project: action.payload
        });
      }

    case _types.DELETE_PROJECT:
      {
        return _objectSpread({}, state, {
          projects: state.projects.filter(function (project) {
            return project.projectIdentifier !== action.payload;
          })
        });
      }

    default:
      return state;
  }
}