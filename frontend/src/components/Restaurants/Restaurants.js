
import { Switch, Route, useRouteMatch } from 'react-router-dom';
import DetailPage from './DetailPage/DetailPage';
import ChoosePage from './ChoosePage/ChoosePage';
import { ChooseProvider } from './ChoosePage/choosepagecontext';

function Restaurants() {
    let match = useRouteMatch();

    return (
    <div>
        <Switch>
            <Route path={`${match.path}/:restaurantSlug`}>
                <DetailPage />
            </Route>
            <ChooseProvider>
                <Route path={match.path}>
                    <ChoosePage />
                </Route>
            </ChooseProvider>
        </Switch>
    </div>
    );
}

export default Restaurants;