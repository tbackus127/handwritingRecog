# CIS 421 - Artificial Intelligence
### Handwriting Recognition Using a Neural Network

This project is created for the Artificial Intelligence class at SUNY Potsdam.

## Group Members:
* Timothy Backus: http://github.com/tbackus127
* Tyler Fiacco: https://github.com/fiaccotd197
* Eric Sakshaug: http://github.com/EricSakshaug11
* Jared Durant: http://github.com/durantjm198

## Detailed Description

This project is centered around using computer vision as a means of reading and parsing human handwriting. Our project's scope is only centered around being able to read handwriting, however, it could easily be implemented with other software to perform more useful tasks such as translation between languages, searching, and spell-checking. We are interested in working with the real world for our project, so the idea of using computer vision to interact with the physical world was central to our brainstorming process. We were drawn to the idea of anybody being able to use and understand our software; we would like people to use our software in any field, rather than focusing on a niche aspect of computing research. This project could help students with digitizing their class notes, allow professionals in any field to speed up their organizational process, and provide a means for language barriers to be broken with the aid of translation software. This project will also help us broaden our knowledge regarding artificial intelligence, as it relates to computer vision.

This project deals with the real-world problem of handwriting recognition; since everybody’s handwriting is different, this poses a problem in terms of a specific algorithm to follow. Since it would be impossible to develop an algorithm that will correctly read and parse human writing 100% of the time, this problem falls under the issue of artificial intelligence. In order to conquer this problem, our team will have to research neural networks, and how to train one to discern the sometimes subtle differences between lowercase letters in the English alphabet. In order to keep this project doable in one semester, we have set our goal to successfully match 90% of reasonably legible characters. This goal may change in the future to adjust for the time constraint. Once the semester ends, work may continue on this project to expand it and have it recognize characters other than the lowercase alphabet, or potentially difficult-to-read handwriting. Ideally, the software might even be able to process the illegible penmanship of computer science undergraduates.

In order to get a wide enough dataset, we plan to have various people across campus write out the alphabet in 26 boxes, scan the paper into a computer to be split into individual images per letter, and then fed into the neural network to train it.

This project also includes autocorrect via the Levanshtein method just in case the neural network fails to correctly identify a letter as a safety net.

## Usage

### Image Splitter

'java -cp bin/ aihw.utils.ImageSplitter <IMAGE_TO_SPLIT>'

26 folders will be created in res/tdata for each letter of the lowercase alphabet. The Nth image in each folder represents the corresponding letter for the Nth row of the image that was split.

### Master Control Panel

'java -cp bin/ aihw.nnet.ControlPanelRunner'

### Recognizer Test

'java -cp bin/ aihw.nnet.NNRecognizer'

### Autocorrect Tester

'java -cp bin/ aihw.autocorrect.AutoCorrectTester [WORD1] [WORD2] [WORD3] ...'

### Word Splitter

'java -cp bin/ aihw.utils.WordSplitter <IMAGE_TO_SPLIT>'

### Word Reader

'java -cp bin/ aihw.utils.WordReader [PATH_TO_WORD_DATA] [TRAINING_DATA_PATH]'
