## Walmart Test App

This app was developed as a part of interview with Walmart Labs.

### Introduction

#### Functionality

This app is composed of 2 screens.

##### **Screen 1:**

- The first screen contains a list of all the products returned by the service call.
- The list supports lazy loading. When you scroll to the bottom of the list, start lazy loading next page of products and append it to the list.

##### Screen 2:

- The second screen displays details of the product.
- Ability to swipe to view next/previous item.

##### API:

- Documentation:  [https://mobile-tha-server.appspot.com](https://mobile-tha-server.appspot.com/)

#### Building

You can open the project in Android studio and press run.

#### Libraries

- [Okhttp](http://square.github.io/okhttp/) for network calls
- [Moshi](https://github.com/square/moshi) to parse JSON into Java Object
- [Glide](https://github.com/bumptech/glide) for image loading
- [Android Architecture Components](https://developer.android.com/arch)

#### License

Copyright 2015 Square, Inc. 

Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at

​	 http://www.apache.org/licenses/LICENSE-2.0 

Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.