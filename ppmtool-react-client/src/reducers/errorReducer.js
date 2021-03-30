import { GET_ERRORS } from "../actions/types";

const initialState = {};

export default function errorReducerFn(state = initialState, action) {
    switch (action.type) {
        case GET_ERRORS:
            return action.payload;

        default:
            return state;
    }
}