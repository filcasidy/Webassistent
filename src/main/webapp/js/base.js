/**
 * @fileoverview
 * Provides methods for the Hello Endpoints sample UI and interaction with the
 * Hello Endpoints API.
 *
 * @author danielholevoet@google.com (Dan Holevoet)
 */

/** google global namespace for Google projects. */
var google = google || {};

/** devrel namespace for Google Developer Relations projects. */
google.devrel = google.devrel || {};

/** samples namespace for DevRel sample code. */
google.devrel.samples = google.devrel.samples || {};

/** hello namespace for this sample. */
google.devrel.samples.hello = google.devrel.samples.hello || {};

/**
 * Client ID of the application (from the APIs Console).
 * @type {string}
 */
google.devrel.samples.hello.CLIENT_ID =
    'replace this with your web client ID';

/**
 * Scopes used by the application.
 * @type {string}
 */
google.devrel.samples.hello.SCOPES =
    'https://www.googleapis.com/auth/userinfo.email';

/**
 * Prints the command response.
 * param {Object} command response to print.
 */
google.devrel.samples.hello.print = function (response) {
    var responseFieldNode = document.getElementById('responseField');
    while (responseFieldNode.firstChild) {
        responseFieldNode.removeChild(responseFieldNode.firstChild);
    }
    var element = document.createElement('div');
    element.innerHTML = response.message;
    document.getElementById('responseField').appendChild(element);
};

/**
 * Fills the suggestions with commands.
 */
google.devrel.fillSuggestions = function () {
    gapi.client.webassistent.webassistent.getSuggestions().execute(
        function (commands) {
            if (!commands.code) {
                for (var i = 0; i < commands.items.length; i++) {
                    var element = document.createElement('option');
                    element.setAttribute('value', commands.items[i].message);
                    document.getElementById('suggestions').appendChild(element);
                }
            }
        });
};


/**
 * Gets a command response via the API.
 * @param {string} id ID of the command.
 */
google.devrel.samples.hello.getGreeting = function (id) {
    if(!id ==''){
    gapi.client.webassistent.webassistent.getServerresponse({'id': id}).execute(
        function (resp) {
            if (!resp.code) {
                google.devrel.samples.hello.print(resp);
            } else {
                window.alert(resp.message);
            }
        });
    } else {
        gapi.client.webassistent.webassistent.noCommandFoundResponse().execute(
            function (noCommandFound) {
                google.devrel.samples.hello.print(noCommandFound);
            }
        )
    }
};

/**
 * Enables the button callbacks in the UI.
 */
google.devrel.samples.hello.enableButtons = function () {
    document.getElementById('submitMessage').onclick = function () {
        google.devrel.samples.hello.getGreeting(
            document.getElementById('messageField').value);
    }
};
/**
* Initializes the application.
* @param {string} apiRoot Root of the API's path.
*/
google.devrel.samples.hello.init = function (apiRoot) {
    // Loads the helloworld API asynchronously, and triggers login
    // when they have completed.
    console.log('ready');
    var apisToLoad;
    var callback = function () {
        if (--apisToLoad == 0) {
            google.devrel.samples.hello.enableButtons();
            google.devrel.fillSuggestions();
        }
    };

    apisToLoad = 1; // must match number of calls to gapi.client.load()
    gapi.client.load('webassistent', 'v1', callback, apiRoot);
};
