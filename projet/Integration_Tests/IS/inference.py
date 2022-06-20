# -*- coding: utf-8 -*-
"""
Created on Wed Feb  2 23:25:22 2022

@author: phima
"""


##############################IMPORTS##############################

import sys
import torch
from torchvision import models, transforms
from PIL import Image
import torch.nn as nn

device = torch.device("cuda:0" if torch.cuda.is_available() else "cpu")


##############################PREPROCESSING##############################

#prend l'adresse de l'image dans les arguments
image_path = sys.argv[1]

#resize the image to what the model was trained on and convert it to a tensor
"""
data_transform = transforms.Compose([
    transforms.Resize(256),
    transforms.CenterCrop(224),
    transforms.ToTensor(),
    transforms.Normalize([0.485, 0.456, 0.406], [0.229, 0.224, 0.225])
])
"""

# First prepare the transformations: resize the image to what the model was trained on and convert it to a tensor
data_transform = transforms.Compose([transforms.Resize((224, 224)), transforms.ToTensor()])
normalize = transforms.Normalize(mean=[0.485, 0.456, 0.406],std=[0.229, 0.224, 0.225])
test_transf = transforms.Compose([transforms.ToPILImage(),
                                  transforms.ToTensor(),normalize])

# Load the image
image = Image.open(image_path)
# Now apply the transformation, expand the batch dimension, and send the image to the GPU
image = data_transform(image).unsqueeze(0).cuda()


##############################MODEL##############################

PATH = 'D:/Users/jonat/TelecomParis/PACT/Projet/projet/IA/whole_model_heavy2.pth'

labels = ["classe_0", "classe_1", "classe_2", "classe_3"]

PATH = 'd:/Users/jonat/TelecomParis/PACT/Projet/projet/IA/whole_model_heavy2.pth'

model_ft2 = torch.load(PATH)
print(model_ft2)

#loaded_model = models.resnet18(pretrained=True)
#num_ftrs = loaded_model.fc.in_features
# mettre: nn.Linear(num_ftrs, len(class_names)).
#loaded_model.fc = nn.Linear(num_ftrs, 4)
#loaded_model = loaded_model.to(device)

#downloading the model will take a bit on the first run, but after it's fast
#loaded_model.load_state_dict(torch.load(PATH))
# Send the model to the GPU 
loaded_model.cuda()
loaded_model.eval()


##############################SORTIE##############################

out = loaded_model(image)
# Find the predicted class
predicted_class = labels[out.argmax()]
print("Predicted class is: {}".format(predicted_class))




