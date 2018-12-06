import {AuthServiceConfig, FacebookLoginProvider} from 'angularx-social-login';
export function getAuthServiceConfigs() {
  let config = new AuthServiceConfig([
    {
      id: FacebookLoginProvider.PROVIDER_ID,
      provider: new FacebookLoginProvider('1369087726569175')
    }
  ]);
  return config;
}
// social step 3
