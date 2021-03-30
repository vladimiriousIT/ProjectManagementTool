"use strict";

Object.defineProperty(exports, "__esModule", {
  value: true
});
exports.deleteProjectTask = exports.updateProjectTask = exports.getProjectTask = exports.getBacklog = exports.addProjectTask = void 0;

var _axios = _interopRequireDefault(require("axios"));

var _types = require("./types");

function _interopRequireDefault(obj) { return obj && obj.__esModule ? obj : { "default": obj }; }

var addProjectTask = function addProjectTask(backlog_id, project_task, history) {
  return function _callee(dispatch) {
    return regeneratorRuntime.async(function _callee$(_context) {
      while (1) {
        switch (_context.prev = _context.next) {
          case 0:
            _context.prev = 0;
            _context.next = 3;
            return regeneratorRuntime.awrap(_axios["default"].post("http://localhost:8080/api/backlog/".concat(backlog_id), project_task));

          case 3:
            history.push("/projectBoard/".concat(backlog_id));
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
}; //export const getBacklog = (backlog_id) => async dispatch => {


exports.addProjectTask = addProjectTask;

var getBacklog = function getBacklog(backlog_id) {
  return function _callee2(dispatch) {
    var res;
    return regeneratorRuntime.async(function _callee2$(_context2) {
      while (1) {
        switch (_context2.prev = _context2.next) {
          case 0:
            _context2.prev = 0;
            _context2.next = 3;
            return regeneratorRuntime.awrap(_axios["default"].get("http://localhost:8080/api/backlog/".concat(backlog_id)));

          case 3:
            res = _context2.sent;
            dispatch({
              type: _types.GET_BACKLOG,
              payload: res.data
            });
            _context2.next = 10;
            break;

          case 7:
            _context2.prev = 7;
            _context2.t0 = _context2["catch"](0);
            dispatch({
              type: _types.GET_ERRORS,
              payload: _context2.t0.res.data
            });

          case 10:
          case "end":
            return _context2.stop();
        }
      }
    }, null, null, [[0, 7]]);
  };
};

exports.getBacklog = getBacklog;

var getProjectTask = function getProjectTask(backlog_id, pt_id, history) {
  return function _callee3(dispatch) {
    var res;
    return regeneratorRuntime.async(function _callee3$(_context3) {
      while (1) {
        switch (_context3.prev = _context3.next) {
          case 0:
            _context3.prev = 0;
            _context3.next = 3;
            return regeneratorRuntime.awrap(_axios["default"].get("http://localhost:8080/api/backlog/".concat(backlog_id, "/").concat(pt_id)));

          case 3:
            res = _context3.sent;
            dispatch({
              type: _types.GET_PROJECT_TASK,
              payload: res.data
            });
            _context3.next = 10;
            break;

          case 7:
            _context3.prev = 7;
            _context3.t0 = _context3["catch"](0);
            history.push("/dashboard");

          case 10:
          case "end":
            return _context3.stop();
        }
      }
    }, null, null, [[0, 7]]);
  };
};

exports.getProjectTask = getProjectTask;

var updateProjectTask = function updateProjectTask(backlog_id, pt_id, project_task, history) {
  return function _callee4(dispatch) {
    return regeneratorRuntime.async(function _callee4$(_context4) {
      while (1) {
        switch (_context4.prev = _context4.next) {
          case 0:
            _context4.prev = 0;
            _context4.next = 3;
            return regeneratorRuntime.awrap(_axios["default"].patch("http://localhost:8080/api/backlog/".concat(backlog_id, "/").concat(pt_id), project_task));

          case 3:
            history.push("/projectBoard/".concat(backlog_id));
            dispatch({
              type: _types.GET_ERRORS,
              payload: {}
            });
            _context4.next = 10;
            break;

          case 7:
            _context4.prev = 7;
            _context4.t0 = _context4["catch"](0);
            dispatch({
              type: _types.GET_ERRORS,
              payload: _context4.t0.response.data
            });

          case 10:
          case "end":
            return _context4.stop();
        }
      }
    }, null, null, [[0, 7]]);
  };
};

exports.updateProjectTask = updateProjectTask;

var deleteProjectTask = function deleteProjectTask(backlog_id, pt_id) {
  return function _callee5(dispatch) {
    return regeneratorRuntime.async(function _callee5$(_context5) {
      while (1) {
        switch (_context5.prev = _context5.next) {
          case 0:
            if (!window.confirm("You are deleting project task ".concat(pt_id, ", this action cannot be undone"))) {
              _context5.next = 4;
              break;
            }

            _context5.next = 3;
            return regeneratorRuntime.awrap(_axios["default"]["delete"]("http://localhost:8080/api/backlog/".concat(backlog_id, "/").concat(pt_id)));

          case 3:
            dispatch({
              type: _types.DELETE_PROJECT_TASK,
              payload: pt_id
            });

          case 4:
          case "end":
            return _context5.stop();
        }
      }
    });
  };
};

exports.deleteProjectTask = deleteProjectTask;