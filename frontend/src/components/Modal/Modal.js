import React from "react";
import {ModalView} from "./ModalView/ModalView";
import {useDispatch} from "react-redux";
import style from './Modal.module.scss';
import {setModal} from "../../store/actions/objectAction";
import {MessageModal} from "./MessageModal/MessageModal";
import {PersonSearch} from "./PersonSearch/PersonSearch";
import {FilterModal} from "./FilterModal/FilterModal";

export const MODAL_VIEW = 'MODAL_VIEW';
export const MODAL_MESSAGE = 'MODAL_MESSAGE';
export const MODAL_PERSON = 'MODAL_PERSON';
export const MODAL_FILTER = "MODAL_FILTER";

export const Modal = ({type, data}) => {
    const dispatch = useDispatch();

    const getModal = (type) => {
        switch (type) {
            default:
                return;
            case MODAL_FILTER:
                return  <FilterModal type={data} />
            case MODAL_PERSON:
                return <PersonSearch />
            case MODAL_VIEW:
                return <ModalView data={data} />
            case MODAL_MESSAGE:
                return <MessageModal
                    type={data.type}
                    message={data.message}
                />
        }
    }

    return (
        <div className={style.Modal}>
            <div className={style.Modal__container}>
                <button
                    onClick={() => {
                        dispatch(setModal({
                            visible: false
                        }))
                    }}
                    className={style.Modal__container__close}
                >
                    +
                </button>
                {getModal(type)}
            </div>
        </div>
    )
}