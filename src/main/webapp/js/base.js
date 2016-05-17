
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
 * Prints a greeting to the greeting log.
 * param {Object} greeting Greeting to print.
 */
google.devrel.samples.hello.print = function(greeting) {
  var element = document.createElement('div');
  element.innerHTML = greeting.message;
  document.getElementById('outputLog').appendChild(element);
};

/**
 * Gets a numbered greeting via the API.
 * @param {string} id ID of the greeting.
 */
google.devrel.samples.hello.getGreeting = function(id) {
  gapi.client.webassistent.webassistentService.getGreeting({'id': id}).execute(
      function(resp) {
        if (!resp.code) {
          google.devrel.samples.hello.print(resp);
        } else {
          window.alert(resp.message);
        }
      });
};

///**
// * Enables the button callbacks in the UI.
// */
google.devrel.samples.hello.enableButtons = function() {
  document.getElementById('getGreeting').onclick = function() {
    google.devrel.samples.hello.getGreeting(
        document.getElementById('id').value);
  }
  };
///**
// * Initializes the application.
// * @param {string} apiRoot Root of the API's path.
// */
google.devrel.samples.hello.init = function(apiRoot) {
  // Loads the helloworld API asynchronously, and triggers login
  // when they have completed.
  console.log('ready');
  var apisToLoad;
  var callback = function() {
    if (--apisToLoad == 0) {
      google.devrel.samples.hello.enableButtons();
    }
  }

  apisToLoad = 1; // must match number of calls to gapi.client.load()
  gapi.client.load('webassistent', 'v1', callback, apiRoot);
};
