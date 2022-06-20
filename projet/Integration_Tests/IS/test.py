# -*- coding: utf-8 -*-
"""
Created on Fri Mar 25 14:41:10 2022

@author: phima
"""


##############################IMPORTS##############################

from __future__ import print_function, division

import torch
import torch.nn as nn
from torchvision import datasets, models, transforms
import matplotlib.pyplot as plt
import time
import os

plt.ion()   # interactive mode


##############################DATA_AND_CPU##############################


# Normalizing for test set
data_transforms = transforms.Compose([
        transforms.Resize(256),
        transforms.CenterCrop(224),
        transforms.ToTensor(),
        transforms.Normalize([0.485, 0.456, 0.406], [0.229, 0.224, 0.225])
    ])

#faire un vrai test set, avec des photos realistes
data_dir = 'd:/Users/jonat/TelecomParis/PACT/Projet/projet/IA/data/plaisir12_04'

image_dataset = datasets.ImageFolder(os.path.join(data_dir), data_transforms)
dataloaders = torch.utils.data.DataLoader(image_dataset, batch_size=4,
                                             shuffle=False, num_workers=0)

dataset_size = len(image_dataset)
class_names = image_dataset.classes


device = torch.device("cuda:0" if torch.cuda.is_available() else "cpu")


##############################TEST_FUNCTION##############################


def test_model(model):
    since = time.time()

    model.eval()   # Set model to evaluate mode

    corrects = 0

    # Iterate over data.
    for inputs, labels in dataloaders:
        inputs = inputs.to(device)
        labels = labels.to(device)
        
        # forward
        with torch.set_grad_enabled(False): #False car pas training phase
            outputs = model(inputs)
            _, predictions = torch.max(outputs, 1)
        
        # statistics
        corrects += torch.sum(predictions == labels.data)
    
    acc = corrects.double() / dataset_size
    print('Acc: {:4f}'.format(acc))
    
    time_elapsed = time.time() - since
    print('Testing complete in {:.0f}m {:.0f}s'.format(
        time_elapsed // 60, time_elapsed % 60))
    
    
##############################MODEL##############################

PATH_model = 'd:/Users/jonat/TelecomParis/PACT/Projet/projet/IA/whole_model_heavy2.pth'
"""
loaded_model = models.resnet18(pretrained=True)
num_ftrs = loaded_model.fc.in_features
# mettre: nn.Linear(num_ftrs, len(class_names)).
loaded_model.fc = nn.Linear(num_ftrs, 4)
loaded_model = loaded_model.to(device)

#downloading the model will take a bit on the first run, but after it's fast
loaded_model.load_state_dict(torch.load(PATH))
# Send the model to the GPU 
loaded_model.cuda()
loaded_model.eval()
"""
model_ft2 = torch.load(PATH_model)
model_ft2.cuda()
model_ft2.eval()

##############################TESTING##############################

test_model(model_ft2)
