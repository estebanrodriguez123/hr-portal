/**
* Copyright (C) 2005-2014 Rivet Logic Corporation.
*
* This program is free software; you can redistribute it and/or
* modify it under the terms of the GNU General Public License
* as published by the Free Software Foundation; version 3
* of the License.
*
* This program is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
* GNU General Public License for more details.
*
* You should have received a copy of the GNU General Public License
* along with this program; if not, write to the Free Software
* Foundation, Inc., 51 Franklin Street, Fifth Floor,
* Boston, MA 02110-1301, USA.
*/

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
    }
);