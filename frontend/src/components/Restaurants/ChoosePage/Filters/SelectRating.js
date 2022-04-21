import { useContext } from 'react';
import { ChooseContext } from '../choosepagecontext';
import './filters.css';

function SelectRating() {
    const chooseCon = useContext(ChooseContext)

    return (
        <div className="select-param">
            <p>Rating</p>
            <select onChange={event => chooseCon.setRating(event.target.value)}>
                <option value='0'>Any</option>
                <option value='1'>&#xf005;</option>
                <option value='2'>&#xf005;&#xf005;</option>
                <option value='3'>&#xf005;&#xf005;&#xf005;</option>
                <option value='4'>&#xf005;&#xf005;&#xf005;&#xf005;</option>
                <option value='5'>&#xf005;&#xf005;&#xf005;&#xf005;&#xf005;</option>
            </select>
        </div>
    );
}

export default SelectRating;