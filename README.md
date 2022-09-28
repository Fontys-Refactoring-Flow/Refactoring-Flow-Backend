<a name="readme-top"></a>

[![Contributors][contributors-shield]][contributors-url]
[![Forks][forks-shield]][forks-url]
[![Issues][issues-shield]][issues-url]

<br />
<div align="center">
  <h3 align="center">Refactoring Flow Backend</h3>

  <p align="center">
    The official backend repository for the Refactoring Flow project.
    <br />
    <br />
	<a href="https://github.com/Fontys-Refactoring-Flow/Refactoring-Flow-Backend/issues">Report Bug</a>
    ·
    <a href="https://github.com/Fontys-Refactoring-Flow/Refactoring-Flow-Backend//issues">Request Feature</a>
  </p>
</div>



<!-- TABLE OF CONTENTS -->
<details>
  <summary>Table of Contents</summary>
  <ol>
    <li>
      <a href="#about-the-project">About The Project</a>
      <ul>
        <li><a href="#built-with">Built With</a></li>
      </ul>
    </li>
    <li>
      <a href="#getting-started">Getting Started</a>
      <ul>
        <li><a href="#prerequisites">Prerequisites</a></li>
        <li><a href="#installation">Installation</a></li>
      </ul>
    </li>
    <li><a href="#usage">Usage</a></li>
    <li><a href="#contributing">Contributing</a></li>
    <li><a href="#license">License</a></li>
    <li><a href="#contact">Contact</a></li>
    <li><a href="#acknowledgments">Acknowledgments</a></li>
  </ol>
</details>



<!-- ABOUT THE PROJECT -->
## About The Project


### Built With

Here's a list of the major frameworks used in our project.

* [![Spring][Spring]][Spring-url]

<p align="right">(<a href="#readme-top">back to top</a>)</p>

<!-- GETTING STARTED -->
## Getting Started

In this section we will discuss the steps which need to be made in order to run the project for the first time.

### Prerequisites

_This  list shows the prerequisites needed to run the project._
* java 17/18
* mysql

### Installation

_Below is an instruction on how to install the project for it's first time run._

1. Fork / clone the repo
   ```sh
   git clone https://github.com/Fontys-Refactoring-Flow/Refactoring-Flow-Backend.git
   ```
2. Edit database settings in application.properties file to use your own
database.
3. Package the project
   ```sh
   mvnw package
   ```
4. Run packaged jar file
   ```sh
   java -jar target/Refactoring-Flow-Backend-0.0.1-SNAPSHOT.jar 
   ```

<p align="right">(<a href="#readme-top">back to top</a>)</p>

<!-- ROADMAP -->
## Roadmap

- [ ] Implement JWT tokens
- [ ] Import code from Git repository
- [ ] Give teacher ability to give feedback to students
- [ ] Make the student be able to see the feedback given by the teacher. 

See the [open issues](https://github.com/Fontys-Refactoring-Flow/Refactoring-Flow-Backend/issues) for a full list of proposed features (and known issues).

<p align="right">(<a href="#readme-top">back to top</a>)</p>

<!-- CONTRIBUTING -->
## Contributing

Contributions are what make the open source community such an amazing place to learn, inspire, and create. Any contributions you make are **greatly appreciated**.

If you have a suggestion that would make this better, please fork the repo and create a pull request. You can also simply open an issue with the tag "enhancement".
Don't forget to give the project a star! Thanks again!

1. Fork the Project
2. Create your Feature Branch (`git checkout -b feature/NewFeature`)
3. Commit your Changes (`git commit -m 'Add some feature'`)
4. Push to the Branch (`git push origin feature/NewFeature`)
5. Open a Pull Request

<p align="right">(<a href="#readme-top">back to top</a>)</p>



<!-- LICENSE -->
## License

Distributed under the MIT License. See `LICENSE.md` for more information.

<p align="right">(<a href="#readme-top">back to top</a>)</p>



<!-- CONTACT -->
## Contact

Project Link: [https://github.com/Fontys-Refactoring-Flow/Refactoring-Flow-Backend](https://github.com/Fontys-Refactoring-Flow/Refactoring-Flow-Backend)

<p align="right">(<a href="#readme-top">back to top</a>)</p>

<!-- MARKDOWN LINKS & IMAGES -->
<!-- https://www.markdownguide.org/basic-syntax/#reference-style-links -->
[contributors-shield]: https://img.shields.io/github/contributors/Fontys-Refactoring-Flow/Refactoring-Flow-Backend.svg?style=for-the-badge
[contributors-url]: https://github.com/Fontys-Refactoring-Flow/Refactoring-Flow-Backend/graphs/contributors
[forks-shield]: https://img.shields.io/github/forks/Fontys-Refactoring-Flow/Refactoring-Flow-Backend.svg?style=for-the-badge
[forks-url]: https://github.com/Fontys-Refactoring-Flow/Refactoring-Flow-Backend/network/members
[stars-shield]: https://img.shields.io/github/stars/Fontys-Refactoring-Flow/Refactoring-Flow-Backend?style=for-the-badge
[stars-url]: https://github.com/Fontys-Refactoring-Flow/Refactoring-Flow-Backend/stargazers
[issues-shield]: https://img.shields.io/github/issues/Fontys-Refactoring-Flow/Refactoring-Flow-Backend?style=for-the-badge
[issues-url]: https://github.com/Fontys-Refactoring-Flow/Refactoring-Flow-Backend/issues
[license-shield]: https://img.shields.io/github/license/Fontys-Refactoring-FlowRefactoring-Flow-Backend?style=for-the-badge
[license-url]: https://github.com/Fontys-Refactoring-Flow/Refactoring-Flow-Backend/blob/master/LICENSE.MD
[Spring]: https://img.shields.io/badge/Spring-6DB33F?style=for-the-badge&logo=spring&logoColor=white
[Spring-url]: https://spring.io/
[React.js]: https://img.shields.io/badge/React-20232A?style=for-the-badge&logo=react&logoColor=61DAFB
[React-url]: https://reactjs.org/
