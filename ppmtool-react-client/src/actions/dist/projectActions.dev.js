"use strict";

Object.defineProperty(exports, "__esModule", {
  value: true
});
exports.deleteProject = exports.getProject = exports.getProjects = exports.createProject = void 0;

var _axios = _interopRequireDefault(require("axios"));

var _types = require("./types");

function _interopRequireDefault(obj) { return obj && obj.__esModule ? obj : { "default": obj }; }

var createProject = function createProject(project, history) {
  return function _callee(dispatch) {
    return regeneratorRuntime.async(function _callee$(_context) {
      while (1) {
        switch (_context.prev = _context.next) {
          case 0:
            _context.prev = 0;
            _context.next = 3;
            return regeneratorRuntime.awrap(_axios["default"].post("http://localhost:8080/api/project", project));

          case 3:
            history.push("/dashboard");
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

exports.createProject = createProject;

var getProjects = function getProjects() {
  return function _callee2(dispatch) {
    var res;
    return regeneratorRuntime.async(function _callee2$(_context2) {
      while (1) {
        switch (_context2.prev = _context2.next) {
          case 0:
            _context2.next = 2;
            return regeneratorRuntime.awrap(_axios["default"].get("http://localhost:8080/api/project/all"));

          case 2:
            res = _context2.sent;
            dispatch({
              type: _types.GET_PROJECTS,
              payload: res.data
            });

          case 4:
          case "end":
            return _context2.stop();
        }
      }
    });
  };
};

exports.getProjects = getProjects;

var getProject = function getProject(id, history) {
  return function _callee3(dispatch) {
    var res;
    return regeneratorRuntime.async(function _callee3$(_context3) {
      while (1) {
        switch (_context3.prev = _context3.next) {
          case 0:
            _context3.prev = 0;
            _context3.next = 3;
            return regeneratorRuntime.awrap(_axios["default"].get("http://localhost:8080/api/project/".concat(id)));

          case 3:
            res = _context3.sent;
            dispatch({
              type: _types.GET_PROJECT,
              payload: res.data
            });
            _context3.next = 10;
            break;

          case 7:
            _context3.prev = 7;
            _context3.t0 = _context3["catch"](0);
            history.push('/dashboard');

          case 10:
          case "end":
            return _context3.stop();
        }
      }
    }, null, null, [[0, 7]]);
  };
};

exports.getProject = getProject;

var deleteProject = function deleteProject(id) {
  return function _callee4(dispatch) {
    return regeneratorRuntime.async(function _callee4$(_context4) {
      while (1) {
        switch (_context4.prev = _context4.next) {
          case 0:
            if (!window.confirm("Are you sure? This will delete the project and all the data related to it")) {
              _context4.next = 4;
              break;
            }

            _context4.next = 3;
            return regeneratorRuntime.awrap(_axios["default"]["delete"]("http://localhost:8080/api/project/".concat(id)));

          case 3:
            dispatch({
              type: _types.DELETE_PROJECT,
              payload: id
            });

          case 4:
          case "end":
            return _context4.stop();
        }
      }
    });
  };
};

exports.deleteProject = deleteProject;