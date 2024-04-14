package io.github.devlcc.core.network.fake

import io.github.devlcc.core.model.ChallengeDayOfTheWeek
import io.github.devlcc.core.network.HomeContentApiService
import io.github.devlcc.core.network.dto.GetActivitiesResponse
import kotlinx.serialization.json.Json
import org.jetbrains.annotations.VisibleForTesting

@VisibleForTesting
class FakeHomeContentApiService(
    private val json: Json,
): HomeContentApiService {

    override suspend fun getActivities(whichDay: ChallengeDayOfTheWeek): GetActivitiesResponse =
        json.decodeFromString(JsonContent)

    companion object {
        internal val JsonContent = """
    {
      "levels": [
        {
          "level": "1",
          "title": "Find your tools",
          "description": "Collect your personalised techniques to beat Anxiety",
          "state": "AVAILABLE",
          "activities": [
            {
              "id": "2ECefjj9gotSu1RzQYguQV7FBMJel296NaotMcf3PwJ432uh72",
              "challengeId": "2ECefjj9gotSu1RzQYguQV",
              "type": "PRACTICE",
              "title": "Break your worry chain reaction",
              "titleB": null,
              "description": "When feeling anxious we tend to worry on repeat. And the more we worry, the more we feel anxious. It’s a vicious cycle. Let’s learn how to break out of it early.",
              "descriptionB": null,
              "state": "NOT_SET",
              "icon": {
                "file": {
                  "url": "//assets.ctfassets.net/37k4ti9zbz4t/DVQrkzmSp53EXqmFn9z1L/f4270b3b29c508c04493ead947e8651f/Chapter_01__Lesson_02__State_Active.pdf",
                  "details": {
                    "size": 5998
                  },
                  "fileName": "Chapter_01__Lesson_02__State_Active.pdf",
                  "contentType": "application/pdf"
                },
                "title": "Chapter=01, Lesson=02, State=Active",
                "description": ""
              },
              "lockedIcon": {
                "file": {
                  "url": "//assets.ctfassets.net/37k4ti9zbz4t/1aknm1E9St7J2mIKPeerI8/e937194308275460c2facda7d09cf9e7/Chapter_01__Lesson_02__State_Locked.pdf",
                  "details": {
                    "size": 5998
                  },
                  "fileName": "Chapter_01__Lesson_02__State_Locked.pdf",
                  "contentType": "application/pdf"
                },
                "title": "Chapter=01, Lesson=02, State=Locked",
                "description": ""
              }
            },
            {
              "id": "71c46gzRB5mCgBYvNEwFYo7FBMJel296NaotMcf3PwJ432uh72",
              "challengeId": "71c46gzRB5mCgBYvNEwFYo",
              "type": "PRACTICE",
              "title": "Find techniques to overcome anxiety",
              "titleB": null,
              "description": "Taking back your mind from anxiety can be a challenge. But don’t worry, we've got the perfect techniques to help. Let’s find out which techniques work best for you!",
              "descriptionB": null,
              "state": "NOT_SET",
              "icon": {
                "file": {
                  "url": "//assets.ctfassets.net/37k4ti9zbz4t/7qfuLW6KOLr5wARa6y1iiJ/d9fe08d9680ebe8fa1d02b056e9d9f61/Chapter_05__Lesson_02__State_Active.pdf",
                  "details": {
                    "size": 9671
                  },
                  "fileName": "Chapter_05__Lesson_02__State_Active.pdf",
                  "contentType": "application/pdf"
                },
                "title": "Chapter 05 Lesson 02 State Active",
                "description": ""
              },
              "lockedIcon": {
                "file": {
                  "url": "//assets.ctfassets.net/37k4ti9zbz4t/60XAuyMCfdyX8vz3uMwPTW/97811784b538551493416bdc6b279f85/Chapter_05__Lesson_02__State_Locked.pdf",
                  "details": {
                    "size": 9671
                  },
                  "fileName": "Chapter_05__Lesson_02__State_Locked.pdf",
                  "contentType": "application/pdf"
                },
                "title": "Chapter 05 Lesson 02 State Locked",
                "description": ""
              }
            }
          ]
        },
        {
          "level": "2",
          "title": "Understanding your anxiety",
          "description": "Understanding anxiety is the first step towards overcoming it.",
          "state": "LOCKED",
          "activities": [
            {
              "id": "2oB58CKFmXQrF1TpCT0QzV7FBMJel296NaotMcf3PwJ432uh72",
              "challengeId": "2oB58CKFmXQrF1TpCT0QzV",
              "type": "PRACTICE",
              "title": "Find your signs of anxiety",
              "titleB": null,
              "description": "Reflecting on how you felt, thought and acted during an anxious moment is the best way to train your mind to spot those signs next time. So you can catch anxiety before it fully takes over. ",
              "descriptionB": null,
              "state": "NOT_SET",
              "icon": {
                "file": {
                  "url": "//assets.ctfassets.net/37k4ti9zbz4t/yexAVzAgoVKQnFNPcS57M/db312214000929b2238273c2e7c1ee44/Chapter_02__Lesson_01__State_Active__1_.pdf",
                  "details": {
                    "size": 4158
                  },
                  "fileName": "Chapter_02__Lesson_01__State_Active (1).pdf",
                  "contentType": "application/pdf"
                },
                "title": "Chapter 02 Lesson 01 State Active (1)",
                "description": ""
              },
              "lockedIcon": {
                "file": {
                  "url": "//assets.ctfassets.net/37k4ti9zbz4t/6t2elPAgkiDVcO2FBTkEil/3eb231b2de977dd18d714207a894560d/Chapter_02__Lesson_01__State_Locked__1_.pdf",
                  "details": {
                    "size": 4158
                  },
                  "fileName": "Chapter_02__Lesson_01__State_Locked (1).pdf",
                  "contentType": "application/pdf"
                },
                "title": "Chapter 02 Lesson 01 State Locked (1)",
                "description": ""
              }
            },
            {
              "id": "wRbmdaMgLVYSi498jxnCA7FBMJel296NaotMcf3PwJ432uh72",
              "challengeId": "wRbmdaMgLVYSi498jxnCA",
              "type": "COMMIT",
              "title": "Make anxiety your ally",
              "titleB": null,
              "description": "Let's embark on the thrilling adventure of turning anxiety into your positive partner in crime!",
              "descriptionB": null,
              "state": "NOT_SET",
              "icon": {
                "file": {
                  "url": "//assets.ctfassets.net/37k4ti9zbz4t/4gLO4SNpkWwF8t0RFRPTC/34bdc756deee0b3e089d4c7248fec8b5/Chapter_03__Lesson_02__State_Active.pdf",
                  "details": {
                    "size": 18722
                  },
                  "fileName": "Chapter_03__Lesson_02__State_Active.pdf",
                  "contentType": "application/pdf"
                },
                "title": "Chapter 03 Lesson 02 State Active",
                "description": ""
              },
              "lockedIcon": {
                "file": {
                  "url": "//assets.ctfassets.net/37k4ti9zbz4t/31thfjGB33eySuuaqMaZj7/e5694a459af89744c17377313b43cb96/Chapter_03__Lesson_02__State_Locked.pdf",
                  "details": {
                    "size": 18722
                  },
                  "fileName": "Chapter_03__Lesson_02__State_Locked.pdf",
                  "contentType": "application/pdf"
                },
                "title": "Chapter 03 Lesson 02 State Locked",
                "description": ""
              }
            },
            {
              "id": "5txQ2uwDcLCC4uv0ChzSJp7FBMJel296NaotMcf3PwJ432uh72",
              "challengeId": "5txQ2uwDcLCC4uv0ChzSJp",
              "type": "COMMIT",
              "title": "Use controlled breathing to stay calm",
              "titleB": null,
              "description": "When anxiety strikes, our body's natural regulation often goes haywire, especially our breathing, which can become fast and shallow. But fear not! We've got a technique to restore your balance.",
              "descriptionB": null,
              "state": "NOT_SET",
              "icon": {
                "file": {
                  "url": "//assets.ctfassets.net/37k4ti9zbz4t/4L39sWbrD6KPQpoLrJ724R/f1383a10c4e7508f55700939736a4882/Chapter_Techniques__Lesson_01__State_Active.pdf",
                  "details": {
                    "size": 36817
                  },
                  "fileName": "Chapter=Techniques, Lesson=01, State=Active.pdf",
                  "contentType": "application/pdf"
                },
                "title": "Chapter=Techniques, Lesson=01, State=Active",
                "description": ""
              },
              "lockedIcon": {
                "file": {
                  "url": "//assets.ctfassets.net/37k4ti9zbz4t/4MDAOhqWXKaL2569oHBsLd/9466c2a4b007862acd129b3556cf85ad/Chapter_Techniques__Lesson_01__State_Locked.pdf",
                  "details": {
                    "size": 36817
                  },
                  "fileName": "Chapter=Techniques, Lesson=01, State=Locked.pdf",
                  "contentType": "application/pdf"
                },
                "title": "Chapter=Techniques, Lesson=01, State=Locked",
                "description": ""
              }
            },
            {
              "id": "6Q7O1F2ICOJ52X4EG1HQCf7FBMJel296NaotMcf3PwJ432uh72",
              "challengeId": "6Q7O1F2ICOJ52X4EG1HQCf",
              "type": "PRACTICE",
              "title": "Practice calming the body",
              "titleB": null,
              "description": "Let’s see how we can bring our bodies back to calm in an everyday situation such as when we wake up anxious.",
              "descriptionB": null,
              "state": "NOT_SET",
              "icon": {
                "file": {
                  "url": "//assets.ctfassets.net/37k4ti9zbz4t/7qDiI7ntQFi9oS5F2WqA5t/7899225893ae7c7ade3becfbd64ef5da/Chapter_Techniques__Lesson_07__State_Active__2_.pdf",
                  "details": {
                    "size": 13738
                  },
                  "fileName": "Chapter_Techniques__Lesson_07__State_Active (2).pdf",
                  "contentType": "application/pdf"
                },
                "title": "Chapter Techniques Lesson 07 State Active (2)",
                "description": ""
              },
              "lockedIcon": {
                "file": {
                  "url": "//assets.ctfassets.net/37k4ti9zbz4t/4GqNnStqFgiwU6fXVOhJWq/fc77b51cfeff4da0d6f689fce10815ce/Chapter_Techniques__Lesson_07__State_Locked__2_.pdf",
                  "details": {
                    "size": 13738
                  },
                  "fileName": "Chapter_Techniques__Lesson_07__State_Locked (2).pdf",
                  "contentType": "application/pdf"
                },
                "title": "Chapter Techniques Lesson 07 State Locked (2)",
                "description": ""
              }
            },
            {
              "id": "3XsnglZ45vEQ0jFRvqGSG77FBMJel296NaotMcf3PwJ432uh72",
              "challengeId": "3XsnglZ45vEQ0jFRvqGSG7",
              "type": "RECAP",
              "title": "Recap: Understanding your anxiety",
              "titleB": null,
              "description": "An increased understanding of your thoughts, behaviors and body reactions can help you manage anxiety.",
              "descriptionB": null,
              "state": "NOT_SET",
              "icon": {
                "file": {
                  "url": "//assets.ctfassets.net/37k4ti9zbz4t/1QOxqn0IlNMwhDcNEn8s5r/e6898788a3c2dec829a6d8abbb8bed18/Chapter_04__Lesson_03__State_Active__2_.pdf",
                  "details": {
                    "size": 16220
                  },
                  "fileName": "Chapter_04__Lesson_03__State_Active (2).pdf",
                  "contentType": "application/pdf"
                },
                "title": "Chapter 04 Lesson 03 State Active (2)",
                "description": ""
              },
              "lockedIcon": {
                "file": {
                  "url": "//assets.ctfassets.net/37k4ti9zbz4t/Y00VVeRiHOy3HQbO1KyeQ/97378bcc3021d2714344d3cb3ad5d651/Chapter_04__Lesson_03__State_Locked__2_.pdf",
                  "details": {
                    "size": 16220
                  },
                  "fileName": "Chapter_04__Lesson_03__State_Locked (2).pdf",
                  "contentType": "application/pdf"
                },
                "title": "Chapter 04 Lesson 03 State Locked (2)",
                "description": ""
              }
            }
          ]
        },
        {
          "level": "3",
          "title": "Building your toolbox",
          "description": "Develop your first set of techniques to start fighting anxiety",
          "state": "LOCKED",
          "activities": [
            {
              "id": "1VhhSkKChB7LwwXN6KhmLw7FBMJel296NaotMcf3PwJ432uh72",
              "challengeId": "1VhhSkKChB7LwwXN6KhmLw",
              "type": "PRACTICE",
              "title": "Talking back to self talk",
              "titleB": null,
              "description": "Ready to tackle pesky negative self-talk with a touch of magic from Cognitive Behavioural Therapy (CBT)? Together we’ll challenge those thoughts and embrace the power of a kinder inner voice. ",
              "descriptionB": null,
              "state": "NOT_SET",
              "icon": {
                "file": {
                  "url": "//assets.ctfassets.net/37k4ti9zbz4t/2ItcBMX3KNj57ehrCtzDd/4f60c6bf917058558d9f504ec23209bd/Chapter_06__Lesson_03__State_Active.pdf",
                  "details": {
                    "size": 17892
                  },
                  "fileName": "Chapter=06, Lesson=03, State=Active.pdf",
                  "contentType": "application/pdf"
                },
                "title": "Chapter=06, Lesson=03, State=Active",
                "description": ""
              },
              "lockedIcon": {
                "file": {
                  "url": "//assets.ctfassets.net/37k4ti9zbz4t/7dPUWQnpqxFOqTAd0dY9mB/404981c81eaf4981bf3dbaaa06364aac/Chapter_06__Lesson_03__State_Locked.pdf",
                  "details": {
                    "size": 17892
                  },
                  "fileName": "Chapter=06, Lesson=03, State=Locked.pdf",
                  "contentType": "application/pdf"
                },
                "title": "Chapter=06, Lesson=03, State=Locked",
                "description": ""
              }
            },
            {
              "id": "52gqVIT8Ok7LnpOA5TTPQr7FBMJel296NaotMcf3PwJ432uh72",
              "challengeId": "52gqVIT8Ok7LnpOA5TTPQr",
              "type": "COMMIT",
              "title": "Avoid black and white thinking",
              "titleB": null,
              "description": "We often paint our perspective with just two shades, thereby missing the spectrum of colors that represent our complex reality! Spectrum Thinking is a cognitive restructuring technique to expand your view, helping you appreciate the nuances in situations.",
              "descriptionB": null,
              "state": "NOT_SET",
              "icon": {
                "file": {
                  "url": "//assets.ctfassets.net/37k4ti9zbz4t/5xcIiT2SFp3LVwz7GDnqej/3e9fea85fd2f707bf571c629db1d947f/Chapter_Techniques__Lesson_04__State_Active-2.pdf",
                  "details": {
                    "size": 19456
                  },
                  "fileName": "Chapter_Techniques__Lesson_04__State_Active-2.pdf",
                  "contentType": "application/pdf"
                },
                "title": "Chapter Techniques Lesson 04 State Active-2",
                "description": ""
              },
              "lockedIcon": {
                "file": {
                  "url": "//assets.ctfassets.net/37k4ti9zbz4t/5x4CKmbLqMEA9Y23zgMnMm/070651383c5c5c587e272389fdc80d98/Chapter_Techniques__Lesson_04__State_Locked-2.pdf",
                  "details": {
                    "size": 19456
                  },
                  "fileName": "Chapter_Techniques__Lesson_04__State_Locked-2.pdf",
                  "contentType": "application/pdf"
                },
                "title": "Chapter Techniques Lesson 04 State Locked-2",
                "description": ""
              }
            },
            {
              "id": "22QdIjSOmtYz9oNzr2RhCG7FBMJel296NaotMcf3PwJ432uh72",
              "challengeId": "22QdIjSOmtYz9oNzr2RhCG",
              "type": "COMMIT",
              "title": "Relax your legs",
              "titleB": null,
              "description": "Many of us unknowingly store anxiety as tension within our bodies. The \"Relaxing Our Legs\" technique uses Progressive Muscle Relaxation to help release that tension, fostering a cycle of physical and mental relief.",
              "descriptionB": null,
              "state": "NOT_SET",
              "icon": {
                "file": {
                  "url": "//assets.ctfassets.net/37k4ti9zbz4t/1hvIkYy6m3aTtJV6fv5zmT/e703a69ca80632b87798edc9d0b4be2a/Chapter_07__Lesson_02__State_Active.pdf",
                  "details": {
                    "size": 13289
                  },
                  "fileName": "Chapter_07__Lesson_02__State_Active.pdf",
                  "contentType": "application/pdf"
                },
                "title": "Chapter 07 Lesson 02 State Active",
                "description": ""
              },
              "lockedIcon": {
                "file": {
                  "url": "//assets.ctfassets.net/37k4ti9zbz4t/bjK5Tlf8bEXh2uE2prRUC/9d9a341dc2076ceabcc87ac2a6c28102/Chapter_07__Lesson_02__State_Locked.pdf",
                  "details": {
                    "size": 13289
                  },
                  "fileName": "Chapter_07__Lesson_02__State_Locked.pdf",
                  "contentType": "application/pdf"
                },
                "title": "Chapter 07 Lesson 02 State Locked",
                "description": ""
              }
            },
            {
              "id": "DOrMC5DeqOHUIyrmP4Geh7FBMJel296NaotMcf3PwJ432uh72",
              "challengeId": "DOrMC5DeqOHUIyrmP4Geh",
              "type": "COMMIT",
              "title": "Postpone worrying until well",
              "titleB": null,
              "description": "\"Postpone Worrying\" is a technique that empowers you to recognize the impact of your physical state on mental health. It encourages you to say, \"I'll handle these anxious thoughts when I'm feeling better,\" allowing you to focus on physical recovery first. ",
              "descriptionB": null,
              "state": "NOT_SET",
              "icon": {
                "file": {
                  "url": "//assets.ctfassets.net/37k4ti9zbz4t/6H7zJgVpFmW67eFS1qlV8j/e85c3e6aaff2511214937bf6742bae69/Chapter_Techniques__Lesson_06__State_Active.pdf",
                  "details": {
                    "size": 26510
                  },
                  "fileName": "Chapter_Techniques__Lesson_06__State_Active.pdf",
                  "contentType": "application/pdf"
                },
                "title": "Chapter Techniques Lesson 06 State Active",
                "description": ""
              },
              "lockedIcon": {
                "file": {
                  "url": "//assets.ctfassets.net/37k4ti9zbz4t/6TzISfYKuyDs9XhHeU2C6e/f5ff4d872c556536c5b0273aae90c27a/Chapter_Techniques__Lesson_06__State_Locked.pdf",
                  "details": {
                    "size": 26510
                  },
                  "fileName": "Chapter_Techniques__Lesson_06__State_Locked.pdf",
                  "contentType": "application/pdf"
                },
                "title": "Chapter Techniques Lesson 06 State Locked",
                "description": ""
              }
            },
            {
              "id": "3XFZF43y5flhHtJqXmBtc57FBMJel296NaotMcf3PwJ432uh72",
              "challengeId": "3XFZF43y5flhHtJqXmBtc5",
              "type": "PRACTICE",
              "title": "Practice talking back to anxiety",
              "titleB": null,
              "description": "Let’s see how we can use the talking back to anxiety technique to navigate typical anxious thoughts, such as the fear we might lose a relationship.",
              "descriptionB": null,
              "state": "NOT_SET",
              "icon": {
                "file": {
                  "url": "//assets.ctfassets.net/37k4ti9zbz4t/3C9ZsZvibP7N39J9RValYs/2ecffab8fc9af34bbdf5c74ed51b8a8f/Chapter_Techniques__Lesson_02__State_Active__1_.pdf",
                  "details": {
                    "size": 17867
                  },
                  "fileName": "Chapter_Techniques__Lesson_02__State_Active (1).pdf",
                  "contentType": "application/pdf"
                },
                "title": "Chapter Techniques Lesson 02 State Active (1)",
                "description": ""
              },
              "lockedIcon": {
                "file": {
                  "url": "//assets.ctfassets.net/37k4ti9zbz4t/4aIHYbnORKXIrZEFQLAfZS/0233ec982a9b868fc5d6c7432fa9e74d/Chapter_Techniques__Lesson_02__State_Locked__1_.pdf",
                  "details": {
                    "size": 17867
                  },
                  "fileName": "Chapter_Techniques__Lesson_02__State_Locked (1).pdf",
                  "contentType": "application/pdf"
                },
                "title": "Chapter Techniques Lesson 02 State Locked (1)",
                "description": ""
              }
            },
            {
              "id": "3jXU67SxpmtDrH4sIBDexN7FBMJel296NaotMcf3PwJ432uh72",
              "challengeId": "3jXU67SxpmtDrH4sIBDexN",
              "type": "PRACTICE",
              "title": "Recap: Building your toolbox",
              "titleB": null,
              "description": "You’ve made it through level 3, and you’ve started building your toolbox to tackle anxiety, let’s recap what you’ve learnt so far!",
              "descriptionB": null,
              "state": "NOT_SET",
              "icon": {
                "file": {
                  "url": "//assets.ctfassets.net/37k4ti9zbz4t/3qsI11EKN5b3EsdO1ARTYD/47afd0f6ce6da80abf33be5a8ec89178/Chapter_04__Lesson_03__State_Active__2_.pdf",
                  "details": {
                    "size": 16220
                  },
                  "fileName": "Chapter_04__Lesson_03__State_Active (2).pdf",
                  "contentType": "application/pdf"
                },
                "title": "Chapter 04 Lesson 03 State Active (2)",
                "description": ""
              },
              "lockedIcon": {
                "file": {
                  "url": "//assets.ctfassets.net/37k4ti9zbz4t/5f3PYlm6aYMAMCOxyIyFpj/cc02f563d4eb876114ce440c43334772/Chapter_04__Lesson_03__State_Locked__2_.pdf",
                  "details": {
                    "size": 16220
                  },
                  "fileName": "Chapter_04__Lesson_03__State_Locked (2).pdf",
                  "contentType": "application/pdf"
                },
                "title": "Chapter 04 Lesson 03 State Locked (2)",
                "description": ""
              }
            }
          ]
        },
        {
          "level": "4",
          "title": "Tackling anxiety at its source",
          "description": "Expand your toolbox to challenge anxiety head on.",
          "state": "LOCKED",
          "activities": [
            {
              "id": "4Bvp5zOCp3fcZlEL6IutmQ7FBMJel296NaotMcf3PwJ432uh72",
              "challengeId": "4Bvp5zOCp3fcZlEL6IutmQ",
              "type": "PRACTICE",
              "title": "Building awareness",
              "titleB": "Understand more deeply",
              "description": "Reflecting on why you felt anxious and how you reacted when anxious, helps you understand more deeply your anxiety patterns. Let’s do another reflection now and build your awareness.",
              "descriptionB": null,
              "state": "NOT_SET",
              "icon": {
                "file": {
                  "url": "//assets.ctfassets.net/37k4ti9zbz4t/njnrwHY9U6WrjUNCIDdpJ/9a47c77d40543fec9467941688eaa93e/Journey_Procrastination__Chapter_01__Lesson_01__State_Active.pdf",
                  "details": {
                    "size": 17543
                  },
                  "fileName": "Journey_Procrastination__Chapter_01__Lesson_01__State_Active.pdf",
                  "contentType": "application/pdf"
                },
                "title": "Journey Procrastination Chapter 01 Lesson 01 State Active",
                "description": ""
              },
              "lockedIcon": {
                "file": {
                  "url": "//assets.ctfassets.net/37k4ti9zbz4t/2llo98v3rQGni2FTRufC9S/bd0c5fe9b1ad5d9e4d772083aaf1b4bb/Journey_Procrastination__Chapter_01__Lesson_01__State_Locked.pdf",
                  "details": {
                    "size": 17543
                  },
                  "fileName": "Journey_Procrastination__Chapter_01__Lesson_01__State_Locked.pdf",
                  "contentType": "application/pdf"
                },
                "title": "Journey Procrastination Chapter 01 Lesson 01 State Locked",
                "description": ""
              }
            },
            {
              "id": "xL42YEJIeh0ENTvRK9z3S7FBMJel296NaotMcf3PwJ432uh72",
              "challengeId": "xL42YEJIeh0ENTvRK9z3S",
              "type": "COMMIT",
              "title": "Don't be anxious about anxiety",
              "titleB": null,
              "description": "In our journey through life, anxiety is an emotion we'll all encounter. But rather than viewing it as an enemy to be defeated, we can learn to acknowledge and accept it. Ever thought about your thoughts about thinking? Yeah, it's a mouthful!",
              "descriptionB": null,
              "state": "NOT_SET",
              "icon": {
                "file": {
                  "url": "//assets.ctfassets.net/37k4ti9zbz4t/3N3mMDznQRxQ33OZEsUgsh/aa2309e241bcc573a6bd48eb6ae9ec67/Journey_Confidence__Chapter_01__Lesson_06__State_Active.pdf",
                  "details": {
                    "size": 23996
                  },
                  "fileName": "Journey_Confidence__Chapter_01__Lesson_06__State_Active.pdf",
                  "contentType": "application/pdf"
                },
                "title": "Journey Confidence Chapter 01 Lesson 06 State Active",
                "description": ""
              },
              "lockedIcon": {
                "file": {
                  "url": "//assets.ctfassets.net/37k4ti9zbz4t/6Dg9qk5kcPbvaPhgFarSgD/89d58c176e1128e13aa6aae6c2efa5a9/Journey_Confidence__Chapter_01__Lesson_06__State_Locked-2.pdf",
                  "details": {
                    "size": 23993
                  },
                  "fileName": "Journey_Confidence__Chapter_01__Lesson_06__State_Locked-2.pdf",
                  "contentType": "application/pdf"
                },
                "title": "Journey Confidence Chapter 01 Lesson 06 State Locked-2",
                "description": ""
              }
            },
            {
              "id": "2ujAzaPZNQaCoKEv6hVZRv7FBMJel296NaotMcf3PwJ432uh72",
              "challengeId": "2ujAzaPZNQaCoKEv6hVZRv",
              "type": "RECAP",
              "title": "Recap: Tackling anxiety at its source",
              "titleB": null,
              "description": "You’ve made it through level 4, and you’ve started expanding your toolbox to challenge anxiety head on. ",
              "descriptionB": null,
              "state": "NOT_SET",
              "icon": {
                "file": {
                  "url": "//assets.ctfassets.net/37k4ti9zbz4t/2Y45eFS2hV6DU8ZJE1cTPm/b09a763568d49719b0f2d0cf3db30dc2/Chapter_04__Lesson_03__State_Active__2_.pdf",
                  "details": {
                    "size": 16220
                  },
                  "fileName": "Chapter_04__Lesson_03__State_Active (2).pdf",
                  "contentType": "application/pdf"
                },
                "title": "Chapter 04 Lesson 03 State Active (2)",
                "description": ""
              },
              "lockedIcon": {
                "file": {
                  "url": "//assets.ctfassets.net/37k4ti9zbz4t/6KqGGK7Qo0d7ylxEMqPG1I/03fa20b547c1d93648f72ba1913466c6/Chapter_04__Lesson_03__State_Locked__2_.pdf",
                  "details": {
                    "size": 16220
                  },
                  "fileName": "Chapter_04__Lesson_03__State_Locked (2).pdf",
                  "contentType": "application/pdf"
                },
                "title": "Chapter 04 Lesson 03 State Locked (2)",
                "description": ""
              }
            }
          ]
        },
        {
          "level": "5",
          "title": "Dealing with thought spirals",
          "description": "Move towards mastering the tools to bring you tranquility and reduce spiraling.",
          "state": "LOCKED",
          "activities": [
            {
              "id": "693AGr5QOAwvEjOL6FB2457FBMJel296NaotMcf3PwJ432uh72",
              "challengeId": "693AGr5QOAwvEjOL6FB245",
              "type": "COMMIT",
              "title": "Reverse the anxiety habit",
              "titleB": null,
              "description": "If we continually overuse our anxiety muscle, we train our body and mind to create anxiety even when there is no need for it. Let’s learn how to reverse the anxiety habit.",
              "descriptionB": null,
              "state": "NOT_SET",
              "icon": {
                "file": {
                  "url": "//assets.ctfassets.net/37k4ti9zbz4t/5projdxCZof57oYabURKrg/f02106224da58ac456430206b8177ad6/Journey_Confidence__Chapter_01__Lesson_05__State_Active.pdf",
                  "details": {
                    "size": 19980
                  },
                  "fileName": "Journey_Confidence__Chapter_01__Lesson_05__State_Active.pdf",
                  "contentType": "application/pdf"
                },
                "title": "Journey Confidence Chapter 01 Lesson 05 State Active",
                "description": ""
              },
              "lockedIcon": {
                "file": {
                  "url": "//assets.ctfassets.net/37k4ti9zbz4t/Ht4qXK8fmiWkYZV2WJ89W/a4c08191b3dccbdab241adbb2454754a/Journey_Confidence__Chapter_01__Lesson_05__State_Locked-2.pdf",
                  "details": {
                    "size": 19991
                  },
                  "fileName": "Journey_Confidence__Chapter_01__Lesson_05__State_Locked-2.pdf",
                  "contentType": "application/pdf"
                },
                "title": "Journey Confidence Chapter 01 Lesson 05 State Locked-2",
                "description": ""
              }
            },
            {
              "id": "41064wiLbQQVlZTGjejRyG7FBMJel296NaotMcf3PwJ432uh72",
              "challengeId": "41064wiLbQQVlZTGjejRyG",
              "type": "COMMIT",
              "title": "A little help from our friends",
              "titleB": null,
              "description": "Negative self-talk can be a real downer, especially when anxiety strikes. But the good news is - there are people who can help. Yes, even people who don’t live inside this app! (It’s cozy in here).",
              "descriptionB": null,
              "state": "NOT_SET",
              "icon": {
                "file": {
                  "url": "//assets.ctfassets.net/37k4ti9zbz4t/2oQ86bPFWsAONprSyYl55P/e31354f64f20b99b1bd14ae3c0a04ac7/Chapter_Techniques__Lesson_09__State_Active.pdf",
                  "details": {
                    "size": 22648
                  },
                  "fileName": "Chapter=Techniques, Lesson=09, State=Active.pdf",
                  "contentType": "application/pdf"
                },
                "title": "Chapter=Techniques, Lesson=09, State=Active",
                "description": ""
              },
              "lockedIcon": {
                "file": {
                  "url": "//assets.ctfassets.net/37k4ti9zbz4t/YpI9yi2pDL3uluuxeh3IK/a4c0c4ec83cff94a5f52902c542833a2/Chapter_Techniques__Lesson_09__State_Locked.pdf",
                  "details": {
                    "size": 22255
                  },
                  "fileName": "Chapter=Techniques, Lesson=09, State=Locked.pdf",
                  "contentType": "application/pdf"
                },
                "title": "Chapter=Techniques, Lesson=09, State=Locked",
                "description": ""
              }
            },
            {
              "id": "5YFyvacD6WjEKNPAU7jgEr7FBMJel296NaotMcf3PwJ432uh72",
              "challengeId": "5YFyvacD6WjEKNPAU7jgEr",
              "type": "COMMIT",
              "title": "Find five blue things",
              "titleB": null,
              "description": "Anxiety has a knack for making us feel unbalanced, overwhelmed with sensations. Let’s use the \"Take a Look\" technique to tap into our sense of sight, grounding us back to the present and away from the chaos of our thoughts.",
              "descriptionB": null,
              "state": "NOT_SET",
              "icon": {
                "file": {
                  "url": "//assets.ctfassets.net/37k4ti9zbz4t/6eRm2WMzNZkWrA9rQC16wS/0c0cf78de26c8891bdac19de5a59bd5c/Journey_Procrastination__Chapter_02__Lesson_06__State_Active.pdf",
                  "details": {
                    "size": 52465
                  },
                  "fileName": "Journey_Procrastination__Chapter_02__Lesson_06__State_Active.pdf",
                  "contentType": "application/pdf"
                },
                "title": "Journey Procrastination Chapter 02 Lesson 06 State Active",
                "description": ""
              },
              "lockedIcon": {
                "file": {
                  "url": "//assets.ctfassets.net/37k4ti9zbz4t/6xqQ2TfLRSFLnCmhoLUBCY/3c14ef90f84884a8f84a63d750c51793/Journey_Procrastination__Chapter_02__Lesson_06__State_Locked.pdf",
                  "details": {
                    "size": 34841
                  },
                  "fileName": "Journey_Procrastination__Chapter_02__Lesson_06__State_Locked.pdf",
                  "contentType": "application/pdf"
                },
                "title": "Journey Procrastination Chapter 02 Lesson 06 State Locked",
                "description": ""
              }
            },
            {
              "id": "D0lO5eXJJRUqcOQy8OQca7FBMJel296NaotMcf3PwJ432uh72",
              "challengeId": "D0lO5eXJJRUqcOQy8OQca",
              "type": "COMMIT",
              "title": "Cutting catastrophes down to size",
              "titleB": null,
              "description": "Catastrophic thinking can escalate a molehill into a mountain in our minds. \"Cutting Catastrophes Down to Size\" is a technique activity designed to challenge and change these thoughts, shrinking them back to their true proportions.",
              "descriptionB": null,
              "state": "NOT_SET",
              "icon": {
                "file": {
                  "url": "//assets.ctfassets.net/37k4ti9zbz4t/58NMrEwh683C7Y1GCkhU3P/9dbde8fd78663a307bbef939cba20bf9/Chapter_04__Lesson_01__State_Active.pdf",
                  "details": {
                    "size": 19893
                  },
                  "fileName": "Chapter_04__Lesson_01__State_Active.pdf",
                  "contentType": "application/pdf"
                },
                "title": "Chapter 04 Lesson 01 State Active",
                "description": ""
              },
              "lockedIcon": {
                "file": {
                  "url": "//assets.ctfassets.net/37k4ti9zbz4t/1Zd9lQdC73ZfY3czAJAtHp/a893257dfe965b5f222cd900d2ea0a6b/Chapter_04__Lesson_01__State_Locked.pdf",
                  "details": {
                    "size": 19893
                  },
                  "fileName": "Chapter_04__Lesson_01__State_Locked.pdf",
                  "contentType": "application/pdf"
                },
                "title": "Chapter 04 Lesson 01 State Locked",
                "description": ""
              }
            },
            {
              "id": "3ER6phrML7D6WO8RI2wK3G7FBMJel296NaotMcf3PwJ432uh72",
              "challengeId": "3ER6phrML7D6WO8RI2wK3G",
              "type": "PRACTICE",
              "title": "Practice breaking worry spirals",
              "titleB": null,
              "description": "Let’s look at how an everyday worry can spiral out of control, and see what we can do to defend against this.",
              "descriptionB": null,
              "state": "NOT_SET",
              "icon": {
                "file": {
                  "url": "//assets.ctfassets.net/37k4ti9zbz4t/2jQgbtMY8zCYwS9yXP9xLY/8e7ddd5854a0a192a0bf9e1d46a7deeb/Chapter_Techniques__Lesson_05__State_Active__1_.pdf",
                  "details": {
                    "size": 28707
                  },
                  "fileName": "Chapter_Techniques__Lesson_05__State_Active (1).pdf",
                  "contentType": "application/pdf"
                },
                "title": "Chapter Techniques Lesson 05 State Active (1)",
                "description": ""
              },
              "lockedIcon": {
                "file": {
                  "url": "//assets.ctfassets.net/37k4ti9zbz4t/1JrLVNxocgge9dvwR6i4aJ/fe127fa5b395e51dee789062a69f60a6/Chapter_Techniques__Lesson_05__State_Locked__1_.pdf",
                  "details": {
                    "size": 28707
                  },
                  "fileName": "Chapter_Techniques__Lesson_05__State_Locked (1).pdf",
                  "contentType": "application/pdf"
                },
                "title": "Chapter Techniques Lesson 05 State Locked (1)",
                "description": ""
              }
            },
            {
              "id": "2bl9pSJITMue1NZxtPf4kD7FBMJel296NaotMcf3PwJ432uh72",
              "challengeId": "2bl9pSJITMue1NZxtPf4kD",
              "type": "RECAP",
              "title": "Recap: Dealing with thought spirals",
              "titleB": null,
              "description": "You’ve made it through level 5, and you’ve taken a big step towards mastering the tools to bring you tranquility and reduce spiraling. So let’s calmly recap what you’ve learnt so far, you got this!",
              "descriptionB": null,
              "state": "NOT_SET",
              "icon": {
                "file": {
                  "url": "//assets.ctfassets.net/37k4ti9zbz4t/mTR92ij532ddm55k1GDoy/517e8497334c9c1aba2af01e691e2dc2/Chapter_04__Lesson_03__State_Active__2_.pdf",
                  "details": {
                    "size": 16220
                  },
                  "fileName": "Chapter_04__Lesson_03__State_Active (2).pdf",
                  "contentType": "application/pdf"
                },
                "title": "Chapter 04 Lesson 03 State Active (2)",
                "description": ""
              },
              "lockedIcon": {
                "file": {
                  "url": "//assets.ctfassets.net/37k4ti9zbz4t/24veC9bbN2I9oNPWZBFmcs/3d899af5dbfcc17d9f3d6e29e8365112/Chapter_04__Lesson_03__State_Locked__2_.pdf",
                  "details": {
                    "size": 16220
                  },
                  "fileName": "Chapter_04__Lesson_03__State_Locked (2).pdf",
                  "contentType": "application/pdf"
                },
                "title": "Chapter 04 Lesson 03 State Locked (2)",
                "description": ""
              }
            }
          ]
        },
        {
          "level": "6",
          "title": "Defeating your worries",
          "description": "Conquer your concerns and become the CEO of Serenity!",
          "state": "LOCKED",
          "activities": [
            {
              "id": "2K4czMMQ0VVrMN3YFOxMZE7FBMJel296NaotMcf3PwJ432uh72",
              "challengeId": "2K4czMMQ0VVrMN3YFOxMZE",
              "type": "COMMIT",
              "title": "Prioritize your worries",
              "titleB": null,
              "description": "This technique is a concrete strategy to sift through your anxieties, categorize them, and focus on what's truly pressing. By doing so, it relieves the pressure of overwhelming thoughts and channels your energy into productive action.",
              "descriptionB": null,
              "state": "NOT_SET",
              "icon": {
                "file": {
                  "url": "//assets.ctfassets.net/37k4ti9zbz4t/3QlmTKP1MtmBtcJAlB6AOH/4d1330a4216852e6e1905a83195803b5/Journey_Procrastination__Chapter_02__Lesson_04__State_Active.pdf",
                  "details": {
                    "size": 15007
                  },
                  "fileName": "Journey_Procrastination__Chapter_02__Lesson_04__State_Active.pdf",
                  "contentType": "application/pdf"
                },
                "title": "Journey Procrastination Chapter 02 Lesson 04 State Active",
                "description": ""
              },
              "lockedIcon": {
                "file": {
                  "url": "//assets.ctfassets.net/37k4ti9zbz4t/3gD6oIMpJKEodNv6bDRnGj/aa2ff4f26aa0f55f06f56f5a0e45e31c/Journey_Procrastination__Chapter_02__Lesson_04__State_Locked.pdf",
                  "details": {
                    "size": 14576
                  },
                  "fileName": "Journey_Procrastination__Chapter_02__Lesson_04__State_Locked.pdf",
                  "contentType": "application/pdf"
                },
                "title": "Journey Procrastination Chapter 02 Lesson 04 State Locked",
                "description": ""
              }
            },
            {
              "id": "5h9CPPK6LECVwF4VITwx2U7FBMJel296NaotMcf3PwJ432uh72",
              "challengeId": "5h9CPPK6LECVwF4VITwx2U",
              "type": "COMMIT",
              "title": "Work out one win",
              "titleB": null,
              "description": "Overwhelmed by potential mishaps? No worries! We've got \"Work out one win\" to conquer anxiety. By focusing on a doable success, you'll flip your mental script and navigate thought spirals like a boss!",
              "descriptionB": null,
              "state": "NOT_SET",
              "icon": {
                "file": {
                  "url": "//assets.ctfassets.net/37k4ti9zbz4t/1DOt0ruhulfJ8LuvuOLWRq/61fcf130c45f04456e5e2708cf835b32/LV2-4__unlocked.pdf",
                  "details": {
                    "size": 20865
                  },
                  "fileName": "LV2-4, unlocked.pdf",
                  "contentType": "application/pdf"
                },
                "title": "Chapter=02, Lesson=04, State=Active",
                "description": "How to respond to anger?"
              },
              "lockedIcon": {
                "file": {
                  "url": "//assets.ctfassets.net/37k4ti9zbz4t/TWthKsnFgrCYXN6YVnIcN/d04ae007f34d2c57dbe2b143be01039e/LV2-4__locked.pdf",
                  "details": {
                    "size": 21320
                  },
                  "fileName": "LV2-4, locked.pdf",
                  "contentType": "application/pdf"
                },
                "title": "Chapter=02, Lesson=04, State=Locked",
                "description": ""
              }
            },
            {
              "id": "1PNht0zCUqjEb53iaHzfXZ7FBMJel296NaotMcf3PwJ432uh72",
              "challengeId": "1PNht0zCUqjEb53iaHzfXZ",
              "type": "COMMIT",
              "title": "Avoid avoidance",
              "titleB": null,
              "description": "This activity helps us face things we might otherwise dodge, so we can face life head on. So let's face those fears, shake off that extra stress baggage, and grab the steering wheel of our own life adventure! 🚗",
              "descriptionB": null,
              "state": "NOT_SET",
              "icon": {
                "file": {
                  "url": "//assets.ctfassets.net/37k4ti9zbz4t/OLV6qJ09Di9VON5x9ccTp/0ecdcd1b668aa7b53eed28334903f5d0/Journey_Procrastination__Chapter_02__Lesson_03__State_Active.pdf",
                  "details": {
                    "size": 16267
                  },
                  "fileName": "Journey_Procrastination__Chapter_02__Lesson_03__State_Active.pdf",
                  "contentType": "application/pdf"
                },
                "title": "Journey Procrastination Chapter 02 Lesson 03 State Active",
                "description": ""
              },
              "lockedIcon": {
                "file": {
                  "url": "//assets.ctfassets.net/37k4ti9zbz4t/2LBYkTOpWvuDhw1ssHcLb0/1153bb3dfc1248e7ebf4f1db7cc638e9/Journey_Procrastination__Chapter_02__Lesson_03__State_Locked.pdf",
                  "details": {
                    "size": 16267
                  },
                  "fileName": "Journey_Procrastination__Chapter_02__Lesson_03__State_Locked.pdf",
                  "contentType": "application/pdf"
                },
                "title": "Journey Procrastination Chapter 02 Lesson 03 State Locked",
                "description": ""
              }
            },
            {
              "id": "1ZK6eXB8y3Lb2Qe4KiOmO57FBMJel296NaotMcf3PwJ432uh72",
              "challengeId": "1ZK6eXB8y3Lb2Qe4KiOmO5",
              "type": "COMMIT",
              "title": "Counting down from 20",
              "titleB": null,
              "description": "When you’re under stress, you can sometimes experience intense emotional responses even to minor stressors. The \"Counting down from 20\" technique helps you take a moment to regain control when anxiety spikes. ",
              "descriptionB": null,
              "state": "NOT_SET",
              "icon": {
                "file": {
                  "url": "//assets.ctfassets.net/37k4ti9zbz4t/2X0WrXT4qXet729bF6Zeno/56241a21006ff18e2fbf5609d82738b2/Chapter_03__Lesson_04__State_Active.pdf",
                  "details": {
                    "size": 5506
                  },
                  "fileName": "Chapter=03, Lesson=04, State=Active.pdf",
                  "contentType": "application/pdf"
                },
                "title": "Chapter=03, Lesson=04, State=Active",
                "description": ""
              },
              "lockedIcon": {
                "file": {
                  "url": "//assets.ctfassets.net/37k4ti9zbz4t/20npKzl5bCMMrzIimhJ6yA/5e8000162b772f5df95a75b5e02de083/Chapter_03__Lesson_04__State_Locked.pdf",
                  "details": {
                    "size": 3751
                  },
                  "fileName": "Chapter=03, Lesson=04, State=Locked.pdf",
                  "contentType": "application/pdf"
                },
                "title": "Chapter=03, Lesson=04, State=Locked",
                "description": ""
              }
            },
            {
              "id": "6zaPXyAF3weJiFuqQQbjCf7FBMJel296NaotMcf3PwJ432uh72",
              "challengeId": "6zaPXyAF3weJiFuqQQbjCf",
              "type": "RECAP",
              "title": "Recap: Defeating your worries",
              "titleB": null,
              "description": "You’ve made it through level 6, and you’re well on your way to becoming a CEO of serenity!   ",
              "descriptionB": null,
              "state": "NOT_SET",
              "icon": {
                "file": {
                  "url": "//assets.ctfassets.net/37k4ti9zbz4t/3JEOnXyb90Yu872cmX3yez/357c0ce1902b523a47b02e185a2e0116/Chapter_04__Lesson_03__State_Active__2_.pdf",
                  "details": {
                    "size": 16220
                  },
                  "fileName": "Chapter_04__Lesson_03__State_Active (2).pdf",
                  "contentType": "application/pdf"
                },
                "title": "Chapter 04 Lesson 03 State Active (2)",
                "description": ""
              },
              "lockedIcon": {
                "file": {
                  "url": "//assets.ctfassets.net/37k4ti9zbz4t/33eERZOyZyOmyQXyvjF5nf/c1a222f4ea26c66944cbc35e5d000696/Chapter_04__Lesson_03__State_Locked__2_.pdf",
                  "details": {
                    "size": 16220
                  },
                  "fileName": "Chapter_04__Lesson_03__State_Locked (2).pdf",
                  "contentType": "application/pdf"
                },
                "title": "Chapter 04 Lesson 03 State Locked (2)",
                "description": ""
              }
            }
          ]
        },
        {
          "level": "7",
          "title": "Building a calm future",
          "description": "Create better habits so anxiety doesn’t return in strength in the future",
          "state": "LOCKED",
          "activities": [
            {
              "id": "2PIvmwxYa4mVFNP0qR0MQQ7FBMJel296NaotMcf3PwJ432uh72",
              "challengeId": "2PIvmwxYa4mVFNP0qR0MQQ",
              "type": "COMMIT",
              "title": "Ring fence your worries",
              "titleB": null,
              "description": "Ever felt like worries were overwhelming you, taking over every waking moment? “Ring Fence Your Worries” lets you designate a time and place to address these concerns, making sure you respect them without letting them rule you.",
              "descriptionB": null,
              "state": "NOT_SET",
              "icon": {
                "file": {
                  "url": "//assets.ctfassets.net/37k4ti9zbz4t/2Qj4onougUkKp40n5aZUAB/242ef7665e33a675eb2990b1c5408bf0/Chapter_06__Lesson_02__State_Active.pdf",
                  "details": {
                    "size": 83662
                  },
                  "fileName": "Chapter_06__Lesson_02__State_Active.pdf",
                  "contentType": "application/pdf"
                },
                "title": "Chapter 06 Lesson 02 State Active",
                "description": ""
              },
              "lockedIcon": {
                "file": {
                  "url": "//assets.ctfassets.net/37k4ti9zbz4t/1h6gifrZMR7WdEgyWvwq7D/652441fb6bdcf78bd8349f417a294451/Chapter_06__Lesson_02__State_Locked.pdf",
                  "details": {
                    "size": 83662
                  },
                  "fileName": "Chapter_06__Lesson_02__State_Locked.pdf",
                  "contentType": "application/pdf"
                },
                "title": "Chapter 06 Lesson 02 State Locked",
                "description": ""
              }
            },
            {
              "id": "5fKKo8vYYaQVX6O4OF9GUe7FBMJel296NaotMcf3PwJ432uh72",
              "challengeId": "5fKKo8vYYaQVX6O4OF9GUe",
              "type": "COMMIT",
              "title": "Make a plan",
              "titleB": null,
              "description": "Worrying about the future is natural! Evolutionarily, anticipating potential threats and planning responses ensured our survival. But life is inherently uncertain, so, instead of spiraling into what-ifs, let's embrace the power of contingency planning! ",
              "descriptionB": null,
              "state": "NOT_SET",
              "icon": {
                "file": {
                  "url": "//assets.ctfassets.net/37k4ti9zbz4t/01o0uUx5PorNL7OEhphijw/4c5398c1024e5355585e49255de15b0d/Chapter_Techniques__Lesson_08__State_Active-2.pdf",
                  "details": {
                    "size": 9609
                  },
                  "fileName": "Chapter_Techniques__Lesson_08__State_Active-2.pdf",
                  "contentType": "application/pdf"
                },
                "title": "Chapter Techniques Lesson 08 State Active-2",
                "description": ""
              },
              "lockedIcon": {
                "file": {
                  "url": "//assets.ctfassets.net/37k4ti9zbz4t/43Jvw7Wb1sycrTIuH4WDu2/99058e17aae73c58fcfe8175c6289be8/Chapter_Techniques__Lesson_08__State_Locked-2.pdf",
                  "details": {
                    "size": 9609
                  },
                  "fileName": "Chapter_Techniques__Lesson_08__State_Locked-2.pdf",
                  "contentType": "application/pdf"
                },
                "title": "Chapter Techniques Lesson 08 State Locked-2",
                "description": ""
              }
            },
            {
              "id": "5EXpo7nFs48BqOSujassmY7FBMJel296NaotMcf3PwJ432uh72",
              "challengeId": "5EXpo7nFs48BqOSujassmY",
              "type": "COMMIT",
              "title": "Have gratitude for the little things",
              "titleB": null,
              "description": "Let's dive into the world of gratitude! This technique helps us reduce anxiety by appreciating positive events beyond our control. Embrace contentment and worry less with this simple Stoic practice.",
              "descriptionB": null,
              "state": "NOT_SET",
              "icon": {
                "file": {
                  "url": "//assets.ctfassets.net/37k4ti9zbz4t/4n8V0W1c4eH8R02GZS5Rrt/6e656f8f25a0f466a0015b43cd9b006a/Chapter_08__Lesson_02__State_Active.pdf",
                  "details": {
                    "size": 13644
                  },
                  "fileName": "Chapter_08__Lesson_02__State_Active.pdf",
                  "contentType": "application/pdf"
                },
                "title": "Chapter 08 Lesson 02 State Active",
                "description": ""
              },
              "lockedIcon": {
                "file": {
                  "url": "//assets.ctfassets.net/37k4ti9zbz4t/6IY5XE9OiDbhlyRZgrRNCj/aaaeab3a27c70c270eaf9d184c916ae1/Chapter_08__Lesson_02__State_Locked.pdf",
                  "details": {
                    "size": 13644
                  },
                  "fileName": "Chapter_08__Lesson_02__State_Locked.pdf",
                  "contentType": "application/pdf"
                },
                "title": "Chapter 08 Lesson 02 State Locked",
                "description": ""
              }
            },
            {
              "id": "2WNSO4a1ZBAOSEokGtosjY7FBMJel296NaotMcf3PwJ432uh72",
              "challengeId": "2WNSO4a1ZBAOSEokGtosjY",
              "type": "PRACTICE",
              "title": "Recap: Building a calm future",
              "titleB": null,
              "description": "You’ve made it through the last level of the journey, except now you’re the final boss! ",
              "descriptionB": null,
              "state": "NOT_SET",
              "icon": {
                "file": {
                  "url": "//assets.ctfassets.net/37k4ti9zbz4t/Ry4GzAinjRIumXqJSCflX/2e3610cc6ceffd82c56937ad9e5d4206/Chapter_04__Lesson_03__State_Active__2_.pdf",
                  "details": {
                    "size": 16220
                  },
                  "fileName": "Chapter_04__Lesson_03__State_Active (2).pdf",
                  "contentType": "application/pdf"
                },
                "title": "Chapter 04 Lesson 03 State Active (2)",
                "description": ""
              },
              "lockedIcon": {
                "file": {
                  "url": "//assets.ctfassets.net/37k4ti9zbz4t/2zECUx8b0xLMKM5nVBjk5P/1e26f2e4f2df5cb1311ce768b7f9d894/Chapter_04__Lesson_03__State_Locked__2_.pdf",
                  "details": {
                    "size": 16220
                  },
                  "fileName": "Chapter_04__Lesson_03__State_Locked (2).pdf",
                  "contentType": "application/pdf"
                },
                "title": "Chapter 04 Lesson 03 State Locked (2)",
                "description": ""
              }
            }
          ]
        }
      ]
    }
""".trimIndent()
    }
}