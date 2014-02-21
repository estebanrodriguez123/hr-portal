AUI().ready(
    'liferay-hudcrumbs', 'liferay-navigation-interaction', 'liferay-sign-in-modal',
    function(A) {
        /* Search box place holder text change when is not empty */
        var searchButton = A.one('#search-button'), 
            searchTextBox = A.one('#search-form #keywords'),
            searchForm = A.one('#search-form'),
            placeholderText = 'search...';
        if (searchButton && searchTextBox && searchForm) {
            /* Initializes placeholder text */
            searchTextBox.set('value', placeholderText);
            
            searchButton.on('click', function(e) {
                e.preventDefault();
                var searchTextValue = searchTextBox.get('value');
                searchTextBox.set('value', (searchTextValue == placeholderText) ? '' : searchTextValue);
                searchForm.submit();
            });
            searchTextBox.on('focus', function(e) {
                if (searchTextBox.get('value') == placeholderText) {
                    searchTextBox.set('value', '');
                }
            });
            searchTextBox.on('blur', function(e) {
                if (searchTextBox.get('value') == '') {
                    searchTextBox.set('value', placeholderText);
                }
            });
        }
        
        var navigation = A.one('#navigation');

        if (navigation) {
            navigation.plug(Liferay.NavigationInteraction);
        }

        
        /* var navigation = A.one('#navigation');

        if (navigation) {
            navigation.plug(Liferay.NavigationInteraction);
        }

        var siteBreadcrumbs = A.one('#breadcrumbs');

        if (siteBreadcrumbs) {
            siteBreadcrumbs.plug(A.Hudcrumbs);
        }

        var signIn = A.one('li.sign-in a');

        if (signIn && signIn.getData('redirect') !== 'true') {
            signIn.plug(Liferay.SignInModal);
        }*/ 
    }
);