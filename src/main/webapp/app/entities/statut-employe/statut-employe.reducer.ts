import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IStatutEmploye, defaultValue } from 'app/shared/model/statut-employe.model';

export const ACTION_TYPES = {
  FETCH_STATUTEMPLOYE_LIST: 'statutEmploye/FETCH_STATUTEMPLOYE_LIST',
  FETCH_STATUTEMPLOYE: 'statutEmploye/FETCH_STATUTEMPLOYE',
  CREATE_STATUTEMPLOYE: 'statutEmploye/CREATE_STATUTEMPLOYE',
  UPDATE_STATUTEMPLOYE: 'statutEmploye/UPDATE_STATUTEMPLOYE',
  DELETE_STATUTEMPLOYE: 'statutEmploye/DELETE_STATUTEMPLOYE',
  RESET: 'statutEmploye/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IStatutEmploye>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false
};

export type StatutEmployeState = Readonly<typeof initialState>;

// Reducer

export default (state: StatutEmployeState = initialState, action): StatutEmployeState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_STATUTEMPLOYE_LIST):
    case REQUEST(ACTION_TYPES.FETCH_STATUTEMPLOYE):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_STATUTEMPLOYE):
    case REQUEST(ACTION_TYPES.UPDATE_STATUTEMPLOYE):
    case REQUEST(ACTION_TYPES.DELETE_STATUTEMPLOYE):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_STATUTEMPLOYE_LIST):
    case FAILURE(ACTION_TYPES.FETCH_STATUTEMPLOYE):
    case FAILURE(ACTION_TYPES.CREATE_STATUTEMPLOYE):
    case FAILURE(ACTION_TYPES.UPDATE_STATUTEMPLOYE):
    case FAILURE(ACTION_TYPES.DELETE_STATUTEMPLOYE):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_STATUTEMPLOYE_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_STATUTEMPLOYE):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_STATUTEMPLOYE):
    case SUCCESS(ACTION_TYPES.UPDATE_STATUTEMPLOYE):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_STATUTEMPLOYE):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: {}
      };
    case ACTION_TYPES.RESET:
      return {
        ...initialState
      };
    default:
      return state;
  }
};

const apiUrl = 'api/statut-employes';

// Actions

export const getEntities: ICrudGetAllAction<IStatutEmploye> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_STATUTEMPLOYE_LIST,
  payload: axios.get<IStatutEmploye>(`${apiUrl}?cacheBuster=${new Date().getTime()}`)
});

export const getEntity: ICrudGetAction<IStatutEmploye> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_STATUTEMPLOYE,
    payload: axios.get<IStatutEmploye>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IStatutEmploye> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_STATUTEMPLOYE,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IStatutEmploye> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_STATUTEMPLOYE,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  return result;
};

export const deleteEntity: ICrudDeleteAction<IStatutEmploye> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_STATUTEMPLOYE,
    payload: axios.delete(requestUrl)
  });
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
